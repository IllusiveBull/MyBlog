package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.services.LoginService;
import com.example.demo.services.RegisterService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Controller
public class RegisterController {
	@Autowired
	RegisterService registerService;
	
	@RequestMapping(value="/register", method=RequestMethod.GET)
	public ModelAndView Hello() {
		ModelAndView mnv = new ModelAndView();
		mnv.setViewName("register.html");
		return mnv;
	}
	
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public ModelAndView Register(String username, String password) {
		ModelAndView mnv = new ModelAndView();
		if (registerService.Register(username, password)) {
			mnv.setViewName("redirect:/login");
		} else {
			mnv.setViewName("register.html");
			mnv.addObject("isRegisterSuccessful", false);
		}
		return mnv;
	}
	
}