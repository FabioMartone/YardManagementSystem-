package com.sad.yardmanagementsystem.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.sad.yardmanagementsystem.controller.dto.DepositoInfoDto;
import com.sad.yardmanagementsystem.controller.dto.UtenteRegistrationDto;
import com.sad.yardmanagementsystem.model.Deposito;
import com.sad.yardmanagementsystem.model.Utente;


public interface UtenteService extends UserDetailsService{
	Utente save(UtenteRegistrationDto registrationDto);
	boolean email_exists(UtenteRegistrationDto registrationDto);
	void add_orario_disp(DepositoInfoDto depositoDto);
	void delete_orario_disp(DepositoInfoDto depositoDto);
}