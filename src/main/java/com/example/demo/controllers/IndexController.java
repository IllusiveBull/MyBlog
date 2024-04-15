package com.example.demo.controllers;

import java.nio.file.FileSystemNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.services.IndexService;

@Controller
public class IndexController {
	@Autowired
	IndexService indexService;
	
	@RequestMapping(value="", method=RequestMethod.GET)
	public ModelAndView Hello(@SessionAttribute(name="currentAccountId", required=false) String currentAccountId) {
		ModelAndView mnv = new ModelAndView();
		mnv.setViewName("index.html");
		mnv.addObject("currentAccount", indexService.getAccountById(currentAccountId));
		return mnv;
	}
}
