package com.freshvotes.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class DashBoardController {
	
//	@Autowired
//	PasswordEncoder passwordEncoder;

	@GetMapping(value="/")
	//@RequestMapping(value="/", method=RequestMethod.GET)
	public String rootView() {
		return "index";
	}
	
	@GetMapping(value="/dashboard")
	public String dashboard() {
		return "dashboard";
	}
	
}
