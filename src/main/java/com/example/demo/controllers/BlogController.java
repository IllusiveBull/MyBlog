package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.forms.BlogEditForm;
import com.example.demo.services.BlogService;
import com.example.demo.services.IndexService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Controller
public class BlogController {
	@Autowired
	IndexService indexService;
	@Autowired
	BlogService blogService;
	
	@RequestMapping(value="/blog/edit", method=RequestMethod.GET)
	public ModelAndView newBlogHello(@SessionAttribute(name="currentAccountId", required=false) String currentAccountId, @RequestParam(name="uuid", required=false) String uuid) {
		ModelAndView mnv = new ModelAndView("blogEdit.html");
		mnv.addObject("currentAccount", indexService.getAccountById(currentAccountId));
		if (uuid != null && currentAccountId != null) {
			if (blogService.getBlogByUuid(uuid).getAuthor().getId().equals(Integer.valueOf(currentAccountId))) {
				mnv.addObject("blog", blogService.getBlogByUuid(uuid));
			}
		}
		return mnv;
	}
	
	@RequestMapping(value="/blog/save", method=RequestMethod.POST)
	public ModelAndView saveBlog(@SessionAttribute(name="currentAccountId", required=false) String currentAccountId, BlogEditForm blogEditForm) {
		String blogUuid = blogService.saveBlog(blogEditForm, currentAccountId);
		if (blogUuid == "-1")
			//User incorrect
			return new ModelAndView("redirect:/error");
		else {
			return new ModelAndView("redirect:/blog/" + blogUuid);
		}
	}
	
	@RequestMapping(value="/blog/{blogUuid}", method=RequestMethod.GET)
	public ModelAndView blogHello(@PathVariable String blogUuid, @SessionAttribute(name="currentAccountId", required=false) String currentAccountId) {
		blogService.addBlogViewNumber(blogUuid);
		ModelAndView mnv = new ModelAndView("blog.html");
		mnv.addObject("blog", blogService.getBlogByUuid(blogUuid));
		mnv.addObject("currentAccount", indexService.getAccountById(currentAccountId));
		return mnv;
	}
	
	@RequestMapping(value="/blog/delete", method=RequestMethod.POST)
	@ResponseBody
	public String delete(@SessionAttribute(name="currentAccountId", required=true) String currentAccountId, @RequestParam(name="blogUuid") String blogUuid) {
		blogService.deleteBlogByUuid(blogUuid, currentAccountId);
		return "ok";
	}
	
	
}
