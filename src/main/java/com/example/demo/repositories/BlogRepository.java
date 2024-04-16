package com.example.demo.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Blog;

@Repository
public interface BlogRepository extends JpaRepository<Blog, UUID>{
	@Query(value = "SELECT a FROM blog a WHERE a.author.username LIKE %:keyword% OR a.title LIKE %:keyword% OR a.content LIKE %:keyword%", nativeQuery=true)
	List<Blog> searchByKeyword(@Param("keyword") String keyword);
}
