package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.domain.Comment.ChildComment;

public interface ChildCommentrepository extends JpaRepository<ChildComment, Integer>{

	
		@Query(value="SELECT id from childcomment WHERE userid=:principalid",nativeQuery = true)
		List<Integer> getchildcommentsID(int principalid);
		
		void deleteByIdIn(List<Integer> ids);
}
