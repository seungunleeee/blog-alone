package com.example.demo.domain.Comment;

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
import com.example.demo.domain.Article.Article;
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
public class Comment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	private int id;
	@Column(length = 100,nullable = false)
	private String content;
	
	@ManyToOne(fetch = FetchType.LAZY )
	@JoinColumn(name="articleid")
	@JsonIgnoreProperties({"user","comments"})
	private Article article;
	@ManyToOne(fetch = FetchType.EAGER)
	private User user;

	private boolean sameuser;
	
	private LocalDateTime createDateTime;
	
	private LocalDateTime updateDateTime;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy ="comment",cascade =CascadeType.ALL  , orphanRemoval = true)
	private List<ChildComment> childcomments = new ArrayList<>();
	
	
	@PrePersist
	public void createdate() {
		this.createDateTime=LocalDateTime.now();
	}
	

	public void updatedate() {
		this.updateDateTime=LocalDateTime.now();
	}
	
	
	
	
	
	
}
