package com.tuyano.springboot;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HeloController {

	@RequestMapping("/")
	public ModelAndView index(ModelAndView mav) {
		mav.setViewName("index");
		return mav;
	}
	@RequestMapping("/other")
	public String other() {
		return "redirect:/";
	}

	@RequestMapping("/home")
	public String home() {
		return "forward:/";
	}

}