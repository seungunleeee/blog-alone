package com.example.demo.Controller;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.handler.excption.CustomException;
import com.example.demo.handler.excption.CustomValidationException;
import com.example.demo.Service.UserService;
import com.example.demo.Service.ArticleService.ArticleService;
import com.example.demo.auth.PrincipalDetails;
import com.example.demo.domain.User;
import com.example.demo.domain.Article.Article;
import com.example.demo.dto.SignupDto;

import groovy.lang.Binding;
import groovyjarjarantlr4.v4.parse.ANTLRParser.exceptionGroup_return;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class UserController {
	private final UserService userService;
	private final ArticleService articleService;

	@GetMapping({ "/", "/main" })
	public String postMethodName(@AuthenticationPrincipal PrincipalDetails principalDetails, Model model,
			@PageableDefault(size = 7) Pageable pageable) {
		// TODO: process POST request
		Page<Article> articles = articleService.기사가져오기(principalDetails.getUser().getId(), pageable);
		model.addAttribute("articles", articles);
		return "test/main";
	}

	@GetMapping("/auth/signin")
	public String signin() {

		System.out.println("working");

		return "login";
	}

	@GetMapping("/auth/signup")
	public String signup() {

		return "signup";
	}

	@PostMapping("/auth/signup")
	public String postsignup(@Valid SignupDto signupDto, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			Map<String, String> errorMap = new HashMap<>();
			for (FieldError error : bindingResult.getFieldErrors()) {
				errorMap.put(error.getField(), error.getDefaultMessage());
				System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>");
				System.out.println(error.getDefaultMessage());
				System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>");
			}
			throw new CustomValidationException("유효성 검사 실패함", errorMap);
		} else {
			User user = signupDto.toEntity();
			try {
				userService.회원가입(user);
			} catch (Exception e) {
				throw new CustomException("중복된 아이디,비밀번호  입니다.");

			}

			return "login";

		}
	}

	@GetMapping("/update")
	public String UpdateInfoPage() {
		
		
		return "/test/update";
	}

	@PostMapping("/update/user")
	public String UpdateUserInfo(@Valid SignupDto signupDto, BindingResult bindingResult,
			@AuthenticationPrincipal PrincipalDetails principalDetails) {
		if (principalDetails.getUser() == null)throw new CustomValidationException("잘못된접근입니다.", null);

		if (bindingResult.hasErrors()) {
			Map<String, String> errorMap = new HashMap<>();
			for (FieldError error : bindingResult.getFieldErrors()) {
				errorMap.put(error.getField(), error.getDefaultMessage());
				System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>");
				System.out.println(error.getDefaultMessage());
				System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>");
			}
			throw new CustomValidationException("유효성 검사 실패함", errorMap);
		} else {
			User user = signupDto.toEntity();
			try {
				System.out.println(user+"여기까지됨>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
				userService.회원정보수정(principalDetails.getUser().getId(), user);
			} catch (Exception e) {
				throw new CustomException("중복된 아이디,비밀번호  입니다.");

			}
			return "redirect:/main";
		}
	}
	
	
	
	@GetMapping("/delete/user")
	public String DeleteUserInfo(
			@AuthenticationPrincipal PrincipalDetails principalDetails) {
		if (principalDetails.getUser() == null)throw new CustomValidationException("잘못된접근입니다.", null);
		//user가 쓴 childcomment 삭제, comment 삭제 article삭제
		userService.회원탈퇴(principalDetails.getUser().getId());
		
		return "redirect:/logout";
	}

}
