package com.example.demo.services;

import java.util.List;
import java.util.UUID;

import com.example.demo.forms.BlogEditForm;
import com.example.demo.models.Blog;

public interface BlogService {
	public String saveBlog(BlogEditForm blogEditForm, String authorAccountId);
	public Blog getBlogByUuid(String blogUuid);
	public void addBlogViewNumber(String blogUuid);
	public List<Blog> getBlogsByAccountId(String accountId);
	public void deleteBlogByUuid(String blogUuid, String currentAccountId);
	public List<Blog> searchBlogsByKeyword(String keyword);
}
