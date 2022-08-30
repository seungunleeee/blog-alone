package com.example.demo.domain.Article;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;

import com.example.demo.domain.User;
import com.example.demo.domain.Comment.Comment;
import com.example.demo.dto.ArticleDto.ArticleAthorityDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor

public class Article {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(length = 30, nullable = false)
	private String title;
	@Column(length = 100, nullable = false)
	private String content;

	private LocalDateTime createDate;
	private LocalDateTime updateDate;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "userid")

	private User user;

	
	
	
	
	@OneToMany(mappedBy ="article",cascade =CascadeType.ALL  , orphanRemoval = true)
	private List<Comment> comments=new ArrayList<>();
	
	/*
	 * @OneToMany(fetch = FetchType.LAZY , mappedBy = "article",cascade =
	 * CascadeType.ALL, orphanRemoval = true )
	 * 
	 * @JsonIgnoreProperties({"article","user"}) private List<Comment> comments;
	 */
	 
	@PrePersist
	public void createdate() {
		this.createDate = LocalDateTime.now();
	}

	public void updatedate() {
		this.createDate = LocalDateTime.now();
	}

}
