package com.sad.yardmanagementsystem.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sad.yardmanagementsystem.service.UtenteService;

import com.sad.yardmanagementsystem.controller.dto.UtenteRegistrationDto;


@Controller
@RequestMapping("/registration")
public class UserRegistrationController {

	private UtenteService utenteService;

	public UserRegistrationController(UtenteService userService) {
		super();
		this.utenteService = userService;
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
		if (utenteService.email_exists(registrationDto)) {
			return "redirect:/registration?error3";
		}
		int ok_tel=1;
		for (int i = 0; i < registrationDto.getTelefono().length() && (ok_tel==1); i++) {
			if(!Character.isDigit(registrationDto.getTelefono().charAt(i))){
				ok_tel=0;
			}
		}
		if(ok_tel==0) {
			return "redirect:/registration?error2";
		}
		int ok_mail=0;
		int j=0;
		while (j != registrationDto.getMail().length() && registrationDto.getMail().charAt(j) != '@') {
			j = j + 1;
		}
		if (j != registrationDto.getMail().length()) {
			ok_mail = 1;
		}
		while(j != registrationDto.getMail().length() && registrationDto.getMail().charAt(j) != '.') {
			j = j + 1;
		}
		if (j == registrationDto.getMail().length()) {
			ok_mail = 0;
		}
		if (ok_mail == 0) {
			return "redirect:/registration?error1";
		}
		int Digit=0;
		int Upper=0;
		int Lower=0;
		int Symbol=0;
		for (int i = 0; i < registrationDto.getPassword().length() && (Digit==0 || Upper==0 || Lower==0 || Symbol==0); i++) {
			if(Character.isDigit(registrationDto.getPassword().charAt(i))){
				Digit=1;
			}
			else if(Character.isUpperCase(registrationDto.getPassword().charAt(i))){
				Upper=1;
			}
			else if(Character.isLowerCase(registrationDto.getPassword().charAt(i))) {
				Lower=1;
			}
			else {
				Symbol=1;
			}
		}
		if(Digit==0 || Upper==0 || Lower==0 || Symbol==0) {
			return "redirect:/registration?error0";
		}
		utenteService.save(registrationDto);
		return "successful_registration";
		
	}
}
