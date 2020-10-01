package com.springweb.controller;

import java.security.Principal;
import java.util.Map;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.springweb.model.LoginForm;
import com.springweb.svc.HomepageSvc;

@Controller
public class AppController {

	@Autowired
	ServletContext servletContext;

	@Autowired
	private Validator validator;

	@InitBinder
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(validator);
	}

	@Autowired
	HomepageSvc homeSvc;

	@GetMapping(value = "/login")
	public String showLoginPage(Model model) {
		model.addAttribute("loginForm", new LoginForm());
		return "login";
	}

	// authentication and logout is handled by spring security -
	// WebSecurityConfig.java

	@RequestMapping(value = "/displayHomePage", produces = "application/json")
	public String showHomePage(@ModelAttribute("loginForm") LoginForm loginForm, BindingResult result, ModelMap model) {
		model.addAttribute("userId", loginForm.getUserId());
		Map<String, String> countMap = homeSvc.getHomepageCount();
		model.addAttribute("barChartJson", countMap.get("barChartJson"));
		model.addAttribute("pieChartJson", countMap.get("pieChartJson"));
		return "homepage";
	}

	@GetMapping(value = "/submenu1")
	public String displayImportCases(Model model) {
		return "menu1_sub1";
	}
	
	@GetMapping(value = "/accessDenied")
	public ModelAndView accesssDenied(Principal user) {
		ModelAndView model = new ModelAndView();
		if (user != null) {
			model.addObject("msg", "Hi " + user.getName() + ", you do not have permission!");
		} else {
			model.addObject("msg", "You do not have permission!");
		}
		model.setViewName("accessDenied");
		return model;
	}

}
