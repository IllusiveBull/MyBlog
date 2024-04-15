package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.services.BlogService;
import com.example.demo.services.IndexService;

import jakarta.servlet.http.HttpSession;

@Controller
public class SpaceController {
	@Autowired
	IndexService indexService;
	@Autowired
	BlogService blogService;
	
	@RequestMapping(value={"/space/{accountId}", "/space"}, method=RequestMethod.GET)
	public ModelAndView Space(@PathVariable(required=false) String accountId, @SessionAttribute(name="currentAccountId", required=false) String currentAccountId) {
		ModelAndView mnv = new ModelAndView();
		if (accountId == null) {
			if (currentAccountId == null) {
				mnv.setViewName("redirect:/");
			} else {
				mnv.setViewName("redirect:/space/" + currentAccountId);
			}
		} else {
			mnv.setViewName("space.html");
			mnv.addObject("currentAccount", indexService.getAccountById(currentAccountId));
			mnv.addObject("account", indexService.getAccountById(accountId));
			mnv.addObject("blogList", blogService.getBlogsByAccountId(accountId));
		}
		return mnv;
	}
}