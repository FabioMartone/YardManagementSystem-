package net.javaguides.springboot.service;

import org.springframework.security.core.userdetails.UserDetailsService; 

import com.sad.yardmanagementsystem.model.Utente;

import net.javaguides.springboot.web.dto.UserRegistrationDto;

public interface UserService extends UserDetailsService{
	Utente save(UserRegistrationDto registrationDto);
}
