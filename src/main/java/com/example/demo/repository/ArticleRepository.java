package com.example.demo.repository;



import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.domain.Article.Article;

public interface ArticleRepository extends JpaRepository<Article, Integer> {
	
	
	@Query(value= "SELECT * FROM article ORDER BY id desc ", nativeQuery = true)
	Page<Article>GetArticles(Pageable pageable);
	
	@Query(value= "SELECT * FROM article WHERE id=:articleid ",nativeQuery = true)
	Article 단일기사조회(int articleid);
	
	@Query(value="SELECT id from article WHERE userid=:principalid",nativeQuery = true)
	List<Integer> getarticlesID(int principalid);
	
}
