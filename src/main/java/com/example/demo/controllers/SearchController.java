package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.Blog;
import com.example.demo.services.BlogService;

@RestController
public class SearchController {
	@Autowired
	BlogService blogService;
	
	@RequestMapping(value="/search", method=RequestMethod.GET)
	public List<Blog> search(String keyword) {
		return blogService.searchBlogsByKeyword(keyword);
	}
}
