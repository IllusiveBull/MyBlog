package com.example.demo.serviceImps;

import java.io.File;
import java.net.MalformedURLException;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;

import com.example.demo.services.PicService;

@Service
public class PicServiceImp implements PicService{

	@Override
	public Resource getPicByPath(String picPath) {
		File picFile = new File("data/pic/" + picPath);
		if (picFile.exists()) {
			try {
				return new UrlResource("file:" + picFile.getAbsolutePath());
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}

}
