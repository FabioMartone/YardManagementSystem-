package com.sad.yardmanagementsystem.service;

import org.springframework.stereotype.Service;

import com.sad.yardmanagementsystem.controller.dto.DepositoOrarioDto;
import com.sad.yardmanagementsystem.model.Deposito;
import com.sad.yardmanagementsystem.model.Utente;

public interface DepositoService {

	void add_orario_disp(DepositoOrarioDto depositoDto);
	void delete_orario_disp(DepositoOrarioDto depositoDto);
	boolean deposito_exists(DepositoOrarioDto depositoDto);
	boolean orario_deposito_exists(DepositoOrarioDto depositoDto);
	boolean fascia_oraria_exists(DepositoOrarioDto depositoDto);
	boolean is_associated(String email, DepositoOrarioDto depositoDto);
	Deposito add_deposito(String indirizzo, Utente gestore);

}