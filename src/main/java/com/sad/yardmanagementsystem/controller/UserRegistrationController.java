package com.sad.yardmanagementsystem.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sad.yardmanagementsystem.service.UtenteService;

import com.sad.yardmanagementsystem.controller.dto.UtenteRegistrationDto;


@Controller
@RequestMapping("/registration")
public class UserRegistrationController {

	private UtenteService userService;

	public UserRegistrationController(UtenteService userService) {
		super();
		this.userService = userService;
	}
	
	@ModelAttribute("utente")
    public UtenteRegistrationDto userRegistrationDto() {
        return new UtenteRegistrationDto();
    }
	
	@GetMapping
	public String showRegistrationForm() {
		return "registration";
	}
	
	@PostMapping
	public String registerUserAccount(@ModelAttribute("utente") UtenteRegistrationDto registrationDto) {
		userService.save(registrationDto);
		return "redirect:/login";
	}
}
