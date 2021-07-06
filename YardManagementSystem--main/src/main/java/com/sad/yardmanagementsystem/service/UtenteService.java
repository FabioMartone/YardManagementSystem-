package com.sad.yardmanagementsystem.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.sad.yardmanagementsystem.controller.dto.DepositoDto;
import com.sad.yardmanagementsystem.controller.dto.DepositoOrarioDto;
import com.sad.yardmanagementsystem.controller.dto.UtenteRegistrationDto;
import com.sad.yardmanagementsystem.model.Deposito;
import com.sad.yardmanagementsystem.model.Utente;


public interface UtenteService extends UserDetailsService{
	Utente save(UtenteRegistrationDto registrationDto);
	boolean email_exists(UtenteRegistrationDto registrationDto);
	void add_orario_disp(DepositoOrarioDto depositoDto);
	void delete_orario_disp(DepositoOrarioDto depositoDto);
	boolean deposito_exists(DepositoOrarioDto depositoDto);
	boolean orario_deposito_exists(DepositoOrarioDto depositoDto);
	boolean fascia_oraria_exists(DepositoOrarioDto depositoDto);
	boolean is_associated(String email, DepositoOrarioDto depositoDto);
	Deposito add_deposito(DepositoDto depositoDto, Utente utente);
	void add_area(DepositoDto depositoDto);
	Utente loadUserByMail(String email) throws UsernameNotFoundException;
}