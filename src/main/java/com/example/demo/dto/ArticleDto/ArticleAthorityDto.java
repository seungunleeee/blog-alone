package com.example.demo.dto.ArticleDto;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.example.demo.domain.User;
import com.example.demo.domain.Article.Article;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleAthorityDto {

	private int id;
	
	private String title;

	private String content;
	
	private LocalDateTime createDate;
	private LocalDateTime updateDate;
	
	private User user;
	
	private Boolean iswriter;
	
	public ArticleAthorityDto toentity(Article article,boolean iswriter) {
	return ArticleAthorityDto.builder()
			.id(article.getId())
			.title(article.getTitle())
			.content(article.getContent())
			.createDate(article.getCreateDate())
			.updateDate(article.getUpdateDate())
			.user(article.getUser())
			.iswriter(iswriter)
			.build();	
	}
	
	

		
}
