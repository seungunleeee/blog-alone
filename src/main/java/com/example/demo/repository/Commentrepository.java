package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.domain.Comment.Comment;

public interface Commentrepository extends JpaRepository<Comment, Integer>{
		@Query(value=" SELECT * FROM comment WHERE articleid=:articleid ORDER BY id desc", nativeQuery = true)
	List<Comment>getarticles(int articleid);
	
		@Modifying
		@Query(value="DELETE from Comment WHERE articleid=:articleid ")
		void 댓글삭제(int articleid);
	
		@Query(value="SELECT id from Comment WHERE user_id=:principalid ",nativeQuery = true)
		List<Integer> getcommentsID(int principalid);
		
		void deleteByIdIn(List<Integer> ids);
}
