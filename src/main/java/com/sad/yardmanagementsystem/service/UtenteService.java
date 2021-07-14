package com.sad.yardmanagementsystem.service;

import org.springframework.security.core.userdetails.UserDetailsService; 
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.sad.yardmanagementsystem.controller.dto.UtenteRegistrationDto;
import com.sad.yardmanagementsystem.model.Utente;


public interface UtenteService extends UserDetailsService{
	Utente save(UtenteRegistrationDto registrationDto);
	boolean email_exists(UtenteRegistrationDto registrationDto);
	Utente loadUserByMail(String email) throws UsernameNotFoundException;
	Utente loadUserById(Long id);
}