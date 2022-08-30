package com.example.demo.domain;


import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class User  implements Serializable{
	/*
	 * @Id
	 * 
	 * @GeneratedValue(strategy = GenerationType.IDENTITY) private int id;
	 * 
	 * @Column(length = 100,unique = true) private String username;
	 * 
	 * @Column(nullable = false) private String password;
	 * 
	 * @Column(nullable = false) private String name;
	 * 
	 * private String email; private String role; private LocalDateTime createDate ;
	 * 
	 * @PrePersist public void createdate() { this.createDate=LocalDateTime.now(); }
	 */
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(length = 100,unique = true) 
	private String username;
	@Column(nullable = false)
	private String password;
	@Column(nullable = false)
	private String name;
	
	private String email;
	
	private String role;
	
	private LocalDateTime createDate;
	
	@PrePersist public void createdate() { this.createDate=LocalDateTime.now(); }
	


	

}
