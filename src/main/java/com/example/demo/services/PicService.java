package com.example.demo.services;

import org.springframework.core.io.Resource;

public interface PicService {
	public Resource getPicByPath(String picPath);
}
