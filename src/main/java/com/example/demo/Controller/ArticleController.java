package com.example.demo.Controller;


import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.demo.Service.ArticleService.ArticleService;
import com.example.demo.Service.CommentService.CommentService;
import com.example.demo.auth.PrincipalDetails;
import com.example.demo.domain.User;
import com.example.demo.domain.Article.Article;
import com.example.demo.domain.Comment.Comment;
import com.example.demo.dto.ArticleDto.ArticleAthorityDto;
import com.example.demo.dto.ArticleDto.ArticleDto;

import org.springframework.ui.Model;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ArticleController {
	private final ArticleService articleService;
	private final CommentService CommentService;
	
	
	@GetMapping("/article/write")
	public String postMethodName(@AuthenticationPrincipal PrincipalDetails principalDetails
			) {
	
		return "article/write";
	}
	
	
	
	@GetMapping("/article/{articleid}")
	public String SowArticle(@PathVariable int articleid,@AuthenticationPrincipal PrincipalDetails principalDetails
			,Model model
			) {
		System.out.println(1);
		ArticleAthorityDto articleinfo=articleService.기사읽기(articleid,principalDetails.getUser().getId());
		List<Comment> comments=CommentService.댓글불러오기(articleid,principalDetails.getUser().getId());
		
		model.addAttribute("comments", comments);
		model.addAttribute("articleinfo", articleinfo);
		
	
		return "article/view";
	}

	
	@PostMapping("/article/write")
	public String enrollarticle(@Valid ArticleDto articleDto,@AuthenticationPrincipal PrincipalDetails user) throws IOException {
		
		
		articleService.글등록(articleDto , user.getUser().getId());
		
		return "redirect:/main";
	}
	
	
	@PostMapping("/article/update/{articleid}")
	public String updateArticle(@PathVariable int articleid,@Valid ArticleDto articleDto,@AuthenticationPrincipal PrincipalDetails principalDetails) {
		
		articleService.글수정(articleid,articleDto,principalDetails.getUser().getId());
		
		
		
		
		return "redirect:/main";
	}
	
	@GetMapping("/article/delete/{articleid}")
	public String deleteArticle(@PathVariable int articleid) {
		
		articleService.글삭제(articleid);
		
		
		return "redirect:/main";
	}
	
	
	
}
