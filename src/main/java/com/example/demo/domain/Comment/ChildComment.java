package com.example.demo.domain.Comment;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;

import com.example.demo.domain.User;
import com.example.demo.domain.Article.Article;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class ChildComment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	private int id;
	@Column(length = 100,nullable = false)
	private String content;
	
	private boolean sameuser;
	
	private LocalDateTime createDateTime;
	

	private LocalDateTime updateDateTime;
	
	@ManyToOne
	@JoinColumn(name="commentid")
	@JsonIgnoreProperties({"user","comments","toentity","childcomments"})
	private Comment  comment;
	
	@ManyToOne
	@JoinColumn(name="userid")
	private User user;
	

	
	@PrePersist
	public void createdate() {
		this.createDateTime=LocalDateTime.now();
	}
	

	public void updatedate() {
		this.updateDateTime=LocalDateTime.now();
	}
	
	 

}
