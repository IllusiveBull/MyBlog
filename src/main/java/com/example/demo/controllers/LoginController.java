package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.services.LoginService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;




@Controller
public class LoginController {
	@Autowired
	LoginService loginService;
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public ModelAndView Hello() {
		return new ModelAndView("login.html");
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public ModelAndView Login(String username, String password, HttpSession session, HttpServletRequest request) {
		ModelAndView mnv = new ModelAndView();
		String accountId = loginService.checkLoginInfo(username, password);
		if (accountId.equals("-1")) {
			mnv.setViewName("login.html");
			mnv.addObject("isLoginSuccessful", false);
		} else {
			mnv.setViewName("redirect:/space/" + accountId);
			session.setAttribute("currentAccountId", accountId);
		}
		return mnv;
	}
	
	@RequestMapping(value="/logout")
	public String logout(HttpSession session) {
	    session.invalidate();
	    return "redirect:/";
	}
	
}