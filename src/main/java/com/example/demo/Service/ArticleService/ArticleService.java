package com.example.demo.Service.ArticleService;




import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.hibernate.internal.util.beans.BeanInfoHelper.ReturningBeanInfoDelegate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.domain.User;
import com.example.demo.domain.Article.Article;
import com.example.demo.domain.Comment.Comment;
import com.example.demo.dto.ArticleDto.ArticleAthorityDto;
import com.example.demo.dto.ArticleDto.ArticleDto;
import com.example.demo.handler.excption.CustomException;
import com.example.demo.repository.ArticleRepository;
import com.example.demo.repository.Commentrepository;
import com.example.demo.repository.UserRepository;


import groovyjarjarantlr4.v4.parse.ANTLRParser.throwsSpec_return;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ArticleService {
	private final ArticleRepository articleRepository;
	private final UserRepository userRepository;
	private final Commentrepository Commentrepository;
	
	
	@Transactional
	public void 글등록 (ArticleDto ardicleDto , int principalid) {
		
		User user =userRepository.findById(principalid).orElseThrow(()->{
			
			throw new CustomException("없는 user임");
  		});
		
		Article article=ardicleDto.toentity(user);
		
		 article=	articleRepository.save(article);
	if(article!=null)System.out.println("글등록 성공");
	}
	
	
	@Transactional(readOnly = true)
	public Page<Article>기사가져오기(int principalid,Pageable pageable){
		
		Page<Article> articles =articleRepository.GetArticles(pageable);
	
		System.out.println(articles);
		return articles;
	}
	
	
	@Transactional(readOnly = true)
	public ArticleAthorityDto 기사읽기(int articleid , int principalid) {
		ArticleAthorityDto articleAthorityDto=new ArticleAthorityDto();
		
		System.out.println(2);
		Article article=articleRepository.단일기사조회(articleid);
		
		if(article.getUser().getId()==principalid) {
			articleAthorityDto= articleAthorityDto.toentity(article, true);
		return articleAthorityDto;
		}
		System.out.println(3);
		 articleAthorityDto=articleAthorityDto.toentity(article, false);
		return articleAthorityDto ;
	}
		
	@Transactional
	public  Article 글수정(int articleid,ArticleDto articleDto, int principalid) {
		Article article=articleRepository.getById(articleid);
		
		article.setContent(articleDto.getContent());
		article.updatedate();
		article.setTitle(articleDto.getTitle());
		article.setId(article.getId());
		article.setUser(article.getUser());
		
		
		return article;
	}
	
	@Transactional
	public void 글삭제(int articleid) {
		List<Integer> integers=new ArrayList<Integer>();
		//예외처리 해주기
		List<Comment>comments=Commentrepository.getarticles(articleid);//댓글가져오는거임
		for (Comment comment : comments) {
			integers.add(comment.getId());
		}
		Commentrepository.deleteByIdIn(integers);
		articleRepository.deleteById(articleid);	
		
		
		
		
	}
		
	


}
