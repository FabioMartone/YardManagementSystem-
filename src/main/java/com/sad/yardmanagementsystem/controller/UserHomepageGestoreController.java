package com.sad.yardmanagementsystem.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sad.yardmanagementsystem.service.UtenteService;


@Controller
@RequestMapping("/home_gestore")
public class UserHomepageGestoreController {

	private UtenteService userService;

	public UserHomepageGestoreController(UtenteService userService) {
		super();
		this.userService = userService;
	}
		
	@GetMapping
	public String showHomepage() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null && auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("RUOLO_UTENTE_GESTORE"))) {
			return "home_gestore";
		}
		else return "redirect:/home_corriere";
	}
	
}