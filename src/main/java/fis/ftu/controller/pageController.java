package fis.ftu.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class pageController {				
	@RequestMapping("/")
	public String index() {				
		return "index";
	}
	
	@RequestMapping("/survey")
	public String survey(@RequestParam(required = false) Integer id) {		
		return "survey";
	}
	
	@RequestMapping("/create_survey")
	public String create_survey() {		
		return "creatSurvey";
	}
	
	@RequestMapping("/edit_survey")
	public String edit_survey(@RequestParam Integer id) {		
		return "editSurvey";
	}
	
	@RequestMapping("/login")
	public String login() {
		return "login";
	}		
	
	@RequestMapping("/register")
	public String register() {
		return "register";
	}	
	
	@RequestMapping("/admin")
	public String admin() {
		return "admin";
	}
}
