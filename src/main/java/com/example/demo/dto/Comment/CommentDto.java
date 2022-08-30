package com.example.demo.dto.Comment;

import javax.validation.constraints.NotNull;

import com.example.demo.domain.User;
import com.example.demo.domain.Article.Article;
import com.example.demo.domain.Comment.ChildComment;
import com.example.demo.domain.Comment.Comment;


import lombok.Data;

@Data
public class CommentDto {
	@NotNull
	private String content;
	
	
	public Comment toentity(CommentDto commentDto,Article article,User user) {
		return  Comment.builder()
						.user(user)
						.content(commentDto.getContent())
						.article(article)
						.build();
				
	}
	
	public ChildComment tochildentity(CommentDto commentDto,Comment comment,User user) {
		return ChildComment.builder()
				.content(commentDto.content)
				.comment(comment)
				.user(user)
				.build();
		
		
	}
	
	
}
