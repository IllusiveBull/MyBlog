package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.HandlerMapping;

import com.example.demo.services.PicService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class PicController {
	@Autowired
	PicService picService;
	
	@RequestMapping(value="/pic/**", method=RequestMethod.GET)
	public ResponseEntity<Resource> getPic(HttpServletRequest request) {
		String path = extractPathFromPattern(request);
		Resource picResource = picService.getPicByPath(path);
		if (picResource.exists()) {
			return ResponseEntity.ok().body(picResource);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	private String extractPathFromPattern(final HttpServletRequest request){
	    String path = (String) request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
	    String bestMatchPattern = (String) request.getAttribute(HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE);
	    return new AntPathMatcher().extractPathWithinPattern(bestMatchPattern, path);
	}
}
