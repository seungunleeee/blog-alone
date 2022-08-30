package com.example.demo.Service.CommentService;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.example.demo.domain.User;
import com.example.demo.domain.Article.Article;
import com.example.demo.domain.Comment.ChildComment;
import com.example.demo.domain.Comment.Comment;
import com.example.demo.dto.Comment.CommentDto;
import com.example.demo.handler.excption.CustomException;
import com.example.demo.handler.excption.CustomValidationException;
import com.example.demo.repository.ChildCommentrepository;
import com.example.demo.repository.Commentrepository;
import com.example.demo.repository.UserRepository;

import groovyjarjarantlr4.v4.parse.ANTLRParser.throwsSpec_return;
import lombok.RequiredArgsConstructor;



@Service
@RequiredArgsConstructor
public class CommentService {
	private final Commentrepository commentrepository;
	private final UserRepository userRepository;
	private final ChildCommentrepository childcommentRepository;
	@Transactional
	public void 댓글달기(CommentDto commentDto,int articleid,int princialid) {
		
		User user =userRepository.findById(princialid).orElseThrow(()->{
			throw new CustomException("유저 정보를 찾을 수 없습니다.");
			
		});
		if(user!=null) {
			Article article =new Article();
			article.setId(articleid);
			
			Comment comment=commentDto.toentity(commentDto,article,user);
			commentrepository.save(comment);
			System.out.println(comment);
			
		}
		else System.out.println("회원정보가 없습니다!");
		
	}
	
	
	@Transactional
	public List<Comment> 댓글불러오기(int articleid,int id){
		List<Comment> comments=   commentrepository.getarticles(articleid);
	for (Comment comment : comments) {
		if(comment.getUser().getId()==id)comment.setSameuser(true);
		else comment.setSameuser(false);
		for (ChildComment childcomment : comment.getChildcomments()) {
			if(childcomment.getUser().getId()==id)childcomment.setSameuser(true);
			else childcomment.setSameuser(false);
		}
	}
		
		
		return comments;
	}
	
	@Transactional 
	public Comment 댓글수정(CommentDto commentDto , int commentid) {
	Comment comment= commentrepository.findById(commentid).orElseThrow(()->{
		throw new CustomValidationException("이미 삭제된 댓글입니다",null);
	});
		comment.setContent(commentDto.getContent());
		comment.updatedate();
		
		return comment;
	}
	
	@Transactional
	public void 댓글삭제(int commentid) {
		commentrepository.deleteById(commentid);
		
		
	}
		
	
	@Transactional
	public int 자식댓글추가(int parentcommentid, CommentDto commentDto, int princialid) {
		
		User user =new User();
		user.setId(princialid);
		Comment parentComment=commentrepository.findById(parentcommentid).orElseThrow(()->{
			
			throw new CustomException("찾을수 없는 기사입니다.");
			
		});
		ChildComment childComment=commentDto.tochildentity(commentDto, parentComment, user);
		parentComment.getChildcomments().add(childComment);
		childcommentRepository.save(childComment);
		
		return parentComment.getArticle().getId();
	}
	@Transactional
	public int 자식댓글수정(int childcommentid,CommentDto commentDto) {
		ChildComment childcomment =childcommentRepository.findById(childcommentid).orElseThrow(()->{
			
			throw new CustomException("수정할 댓글을 찾을 수 없습니다.");
			
		});
		childcomment.updatedate();
		childcomment.setContent(commentDto.getContent());
		
		return childcomment.getComment().getArticle().getId();
	}
	
	@Transactional
	public int 자식댓글삭제(int childcommentid) {
		 ChildComment childComment=  childcommentRepository.findById(childcommentid).orElseThrow(()->{
				
				throw new CustomException("삭제할 댓글을 찾을 수 없습니다.");
				
			});
		 int articleid= childComment.getComment().getArticle().getId();
		 childcommentRepository.deleteById(childcommentid);
		 
		return articleid;
	}
	
	
	
	
}
