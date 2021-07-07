package com.sad.yardmanagementsystem.service;


import java.util.ArrayList;
import java.util.Arrays; 
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.sad.yardmanagementsystem.model.Utente;
import com.sad.yardmanagementsystem.repository.RepositoryDeposito;
import com.sad.yardmanagementsystem.repository.RepositoryOrarioDisponibile;
import com.sad.yardmanagementsystem.repository.RepositoryRuolo;
import com.sad.yardmanagementsystem.repository.RepositoryUtente;
import com.sad.yardmanagementsystem.model.Area;
import com.sad.yardmanagementsystem.model.Deposito;
import com.sad.yardmanagementsystem.model.Gestore;
import com.sad.yardmanagementsystem.model.OrarioDisponibile;
import com.sad.yardmanagementsystem.model.Ruolo;
import com.sad.yardmanagementsystem.model.TipoArea;
import com.sad.yardmanagementsystem.controller.dto.DepositoDto;
import com.sad.yardmanagementsystem.controller.dto.DepositoOrarioDto;
import com.sad.yardmanagementsystem.controller.dto.UtenteRegistrationDto;


@Service
public class UtenteServiceImpl implements UtenteService{

	private RepositoryUtente userRepository;
		
	@Autowired
	private RepositoryRuolo ruoloRepository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	public UtenteServiceImpl(RepositoryUtente userRepository) {
		super();
		this.userRepository = userRepository;
	}

	@Override
	public Utente save(UtenteRegistrationDto registrationDto) {
		Utente user = new Utente(registrationDto.getRagioneSociale(),registrationDto.getPartitaIVA(),registrationDto.getMail(),passwordEncoder.encode(registrationDto.getPassword()),registrationDto.getTelefono(),registrationDto.getTipologia(),registrationDto.getReferente(),Arrays.asList(ruoloRepository.findByNome(registrationDto.getTipologia())));
		return userRepository.save(user);
	}
	
	public boolean email_exists(UtenteRegistrationDto registrationDto) {
		if (userRepository.findByEmail(registrationDto.getMail()) != null) {
			return true;
		}
		return false;
	}
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
	
		Utente user = userRepository.findByEmail(email);
		if(user == null) {
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));		

	}
	
	@Override
	public Utente loadUserByMail(String email) throws UsernameNotFoundException {
	
		Utente user = userRepository.findByEmail(email);
		if(user == null) {
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		return user;
	}
	
	
	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Ruolo> ruoli){
		return ruoli.stream().map(ruolo -> new SimpleGrantedAuthority(ruolo.getNome())).collect(Collectors.toList());
	}
	
	}