package com.example.demo.serviceImps;

import java.io.File;
import java.io.IOException;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.forms.BlogEditForm;
import com.example.demo.models.Account;
import com.example.demo.models.Blog;
import com.example.demo.repositories.AccountRepository;
import com.example.demo.repositories.BlogRepository;
import com.example.demo.services.BlogService;

@Service
public class BlogServiceImp implements BlogService{
	@Autowired
	AccountRepository accountRepository;
	@Autowired
	BlogRepository blogRepository;

	@Override
	public String saveBlog(BlogEditForm blogEditForm, String authorAccountId) {
		Blog blog;
		if (!blogEditForm.getBlogUuid().isBlank() && blogRepository.existsById(UUID.fromString(blogEditForm.getBlogUuid()))) {
			blog = blogRepository.getById(UUID.fromString(blogEditForm.getBlogUuid()));
			if (!blog.getAuthor().getId().equals(Integer.valueOf(authorAccountId))) {
				return "-1";
			}
			blog.setUpdatedAt(Instant.now());
		} else {
			blog = new Blog();
		}
		blog.setTitle(blogEditForm.getBlogTitle());
		blog.setContent(blogEditForm.getBlogContent());
		blog.setAuthor(accountRepository.getOne(Integer.parseInt(authorAccountId)));
		String blogHeadImageType = blogEditForm.getHeadImageType();
		switch (blogHeadImageType){
			case "file": {
				MultipartFile file = blogEditForm.getHeadImageFile();
				String uploadDir = "data/pic/headImages";
				File directory = new File(uploadDir);
				if (!directory.exists()) {
				    directory.mkdirs();
				}
				String fileName = blog.getUuid().toString() + "." + file.getOriginalFilename().split("\\.")[1];
				File saveFile = new File(directory, fileName);
				try {
					file.transferTo(saveFile.getAbsoluteFile());
					blog.setHeadImage("pic/headImages/" + fileName);
				} catch (IllegalStateException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			}
			case "url": {
				blog.setHeadImage(blogEditForm.getHeadImageUrl());
				break;
			}
			case "none": {
				break;
			}
			default: {}
		}
		blogRepository.save(blog);
		return blog.getUuid().toString();
	}

	@Override
	public Blog getBlogByUuid(String blogUuid) {
		return blogRepository.getById(UUID.fromString(blogUuid));
	}

	@Override
	public void addBlogViewNumber(String blogUuid) {
		Blog blog = blogRepository.getById(UUID.fromString(blogUuid));
		blog.setViews(blog.getViews() + 1);
		blogRepository.save(blog);
	}

	@Override
	public List<Blog> getBlogsByAccountId(String accountId) {
		Account account = accountRepository.getOne(Integer.parseInt(accountId));
		return account.getBlogs();
	}

	@Override
	public void deleteBlogByUuid(String blogUuid, String currentAccountId) {
		Blog blog = blogRepository.getById(UUID.fromString(blogUuid));
		if (blog.getAuthor().getId().equals(Integer.valueOf(currentAccountId))) {
			blogRepository.deleteById(UUID.fromString(blogUuid));
		}
	}
	
	
}
