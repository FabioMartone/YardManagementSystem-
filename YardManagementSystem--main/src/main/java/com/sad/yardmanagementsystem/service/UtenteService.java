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
	boolean deposito_exists(DepositoInfoDto depositoDto);
	boolean orario_deposito_exists(DepositoInfoDto depositoDto);
	boolean fascia_oraria_exists(DepositoInfoDto depositoDto);
	boolean is_associated(String email, DepositoInfoDto depositoDto);
}