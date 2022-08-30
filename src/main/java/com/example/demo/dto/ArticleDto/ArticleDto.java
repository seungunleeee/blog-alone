package com.example.demo.dto.ArticleDto;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

import com.example.demo.domain.User;
import com.example.demo.domain.Article.Article;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class ArticleDto {
	
	@NotBlank
	private String title;
	@NotBlank
	private String content;
	
	public Article toentity(User user) {
		return Article.builder()
				.title(title)
				.content(content)
				.user(user)
				.build();		
		
		
	}

	
	

}
