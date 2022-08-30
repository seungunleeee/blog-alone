package com.example.demo.Controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.Service.CommentService.CommentService;
import com.example.demo.auth.PrincipalDetails;
import com.example.demo.dto.Comment.CommentDto;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class CommentController {
	private final CommentService commentService;
	
	
	@PostMapping("/comment/{articleid}")
	public String addcomment(CommentDto commentDto,@PathVariable int articleid,@AuthenticationPrincipal PrincipalDetails principalDetails) {
		System.out.println(commentDto+"    "+articleid);
		commentService.댓글달기(commentDto, articleid,principalDetails.getUser().getId());
		

		return "redirect:/article/"+articleid;
	}
	@PostMapping("/comment/update/{commentid}")
	public String updatecomment(CommentDto commentDto,@PathVariable int commentid, 
			@AuthenticationPrincipal PrincipalDetails principalDetails) {
		commentService.댓글수정(commentDto,commentid);
		
		
		return "redirect:/main";
	}
	
	@GetMapping("/comment/delete/{commentid}")
	public String DeleteComment(CommentDto commentDto,@PathVariable int commentid, 
			@AuthenticationPrincipal PrincipalDetails principalDetails) {
		commentService.댓글삭제(commentid);
		
		
		return "redirect:/main";
	}
	
	
	
	
	
	
	@PostMapping("childcomment/{parentid}")
	public String writechildComment(@PathVariable int parentid,CommentDto commentDto,
			@AuthenticationPrincipal PrincipalDetails principalDetails) {
		
		int parentarticle=  commentService.자식댓글추가(parentid,commentDto,principalDetails.getUser().getId());
		
		return "redirect:/article/"+parentarticle;
	}
	
	
	
	 @PostMapping("/childcomment/update/{Childcommentid}") 
	 public String UpdateChildComment(@PathVariable int Childcommentid ,CommentDto commentDto) {
		 
		int articleid= commentService.자식댓글수정(Childcommentid,commentDto);
		 
		 return "redirect:/article/"+articleid;
	 }
	 
	 @GetMapping("/childcomment/delete/{Childcommentid}")
	 public String DeleteChildComment(@PathVariable int Childcommentid) {
		 
		 int articleid= commentService.자식댓글삭제(Childcommentid);
		 
		 
		 return "redirect:/article/"+articleid;
	 }
	 
	 
	
	

}
