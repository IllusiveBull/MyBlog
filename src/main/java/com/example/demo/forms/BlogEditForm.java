package com.example.demo.forms;

import org.springframework.web.multipart.MultipartFile;

public class BlogEditForm {
	private String blogUuid;
	private String blogTitle;
	private String blogContent;
	private MultipartFile headImageFile;
	private String headImageUrl;
	private String headImageType;
	public String getBlogTitle() {
		return blogTitle;
	}
	public void setBlogTitle(String blogTitle) {
		this.blogTitle = blogTitle;
	}
	public String getBlogContent() {
		return blogContent;
	}
	public void setBlogContent(String blogContent) {
		this.blogContent = blogContent;
	}
	public MultipartFile getHeadImageFile() {
		return headImageFile;
	}
	public void setHeadImageFile(MultipartFile headImageFile) {
		this.headImageFile = headImageFile;
	}
	public String getHeadImageUrl() {
		return headImageUrl;
	}
	public void setHeadImageUrl(String headImageUrl) {
		this.headImageUrl = headImageUrl;
	}
	public String getHeadImageType() {
		return headImageType;
	}
	public void setHeadImageType(String headImageType) {
		this.headImageType = headImageType;
	}
	public String getBlogUuid() {
		return blogUuid;
	}
	public void setBlogUuid(String blogUuid) {
		this.blogUuid = blogUuid;
	}
	
}