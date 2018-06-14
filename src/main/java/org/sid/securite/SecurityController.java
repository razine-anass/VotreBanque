package org.sid.securite;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SecurityController {
	
	@RequestMapping(value="/login")
	public String login(){
		return "login";
	}

	//Action / c'est l'action par default ca veut lorsqu'on tape http://localhost:8080
	@RequestMapping(value="/")
	public String home(){
		return "redirect:/operations";
	}
	
	@RequestMapping(value="/403")
	public String accessDenied(){
		return "403";
	}
}
