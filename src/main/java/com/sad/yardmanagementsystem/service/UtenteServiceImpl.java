package com.sad.yardmanagementsystem.service;

import java.util.Arrays; 
import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.sad.yardmanagementsystem.model.Utente;
import com.sad.yardmanagementsystem.repository.RepositoryRuolo;
import com.sad.yardmanagementsystem.repository.RepositoryUtente;
import com.sad.yardmanagementsystem.model.Ruolo;
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
	
	@Override
	public Utente loadUserById(Long id) {
		return userRepository.findByid_utente(id);
	}
	
	
	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Ruolo> ruoli){
		return ruoli.stream().map(ruolo -> new SimpleGrantedAuthority(ruolo.getNome())).collect(Collectors.toList());
	}
	
	}