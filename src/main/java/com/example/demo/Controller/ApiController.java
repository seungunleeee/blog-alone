package com.example.demo.Controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Service.ArticleService.ArticleService;
import com.example.demo.auth.PrincipalDetails;
import com.example.demo.dto.ArticleDto.ArticleDto;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ApiController {
	private final ArticleService articleService;
	
	/*
	 * @PostMapping("/article/write") public void enrollarticle(HttpServletResponse
	 * response,@RequestBody ArticleDto articleDto,@AuthenticationPrincipal
	 * PrincipalDetails user) throws IOException {
	 * 
	 * 
	 * articleService.글등록(articleDto , user.getUser().getId()); String
	 * redirecturlString="http://localhost:8000/";
	 * 
	 * 
	 * response.sendRedirect(redirecturlString); }
	 */
	
	

}
