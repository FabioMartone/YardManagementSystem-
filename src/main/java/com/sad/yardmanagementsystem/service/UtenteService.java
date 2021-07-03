package com.sad.yardmanagementsystem.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.sad.yardmanagementsystem.controller.dto.UtenteRegistrationDto;
import com.sad.yardmanagementsystem.model.Utente;


public interface UtenteService extends UserDetailsService{
	Utente save(UtenteRegistrationDto registrationDto);
	
}