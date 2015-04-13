package com.springapp.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class HelloController {
	@RequestMapping(method = RequestMethod.GET)
	public String printWelcome(ModelMap model) {
		model.addAttribute("message", "Hello world!");
		return "hello";
	}


@RequestMapping(value = "/hello", method = RequestMethod.GET)
public String hello(ModelMap model) {
			model.addAttribute("msg", "JCG Hello World!yoyiuyiuyiu");
			return "helloWorld";
	}

@RequestMapping(value = "/displayMessage/{msg}", method = RequestMethod.GET)
public String displayMessage(@PathVariable String msg, ModelMap model) {
			model.addAttribute("msg", msg);
			return "helloWorld";
	}

}