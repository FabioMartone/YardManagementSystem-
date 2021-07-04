package com.sad.yardmanagementsystem.service;


import java.util.Arrays; 
import java.util.Collection;
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
import com.sad.yardmanagementsystem.repository.RepositoryRuolo;
import com.sad.yardmanagementsystem.repository.RepositoryUtente;
import com.sad.yardmanagementsystem.model.Deposito;
import com.sad.yardmanagementsystem.model.OrarioDisponibile;
import com.sad.yardmanagementsystem.model.Ruolo;
import com.sad.yardmanagementsystem.controller.dto.DepositoInfoDto;
import com.sad.yardmanagementsystem.controller.dto.UtenteRegistrationDto;


@Service
public class UtenteServiceImpl implements UtenteService{

	private RepositoryUtente userRepository;
	
	@Autowired
	private RepositoryDeposito depositoRepository;
	
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
	
	public void add_orario_disp(DepositoInfoDto depositoDto) {
		
		Optional<Deposito> dep = depositoRepository.findById(depositoDto.getId());
		
		Deposito deposito = dep.get();
		
		Collection<OrarioDisponibile> orari = deposito.getorariDisponibili();
		
		orari.add(depositoDto.getorario());
		
		deposito.setorariDisponibili(orari);
		
		depositoRepository.save(deposito);
		
	}
	
	public void delete_orario_disp(DepositoInfoDto depositoDto) {
		
		Optional<Deposito> dep = depositoRepository.findById(depositoDto.getId());
		
		Deposito deposito = dep.get();
		
		Collection<OrarioDisponibile> orari = deposito.getorariDisponibili();
		
		orari.remove(depositoDto.getorario());
		
		deposito.setorariDisponibili(orari);
		
		depositoRepository.save(deposito);
		
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
	
		Utente user = userRepository.findByEmail(email);
		if(user == null) {
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));		

	}
	
	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Ruolo> ruoli){
		return ruoli.stream().map(ruolo -> new SimpleGrantedAuthority(ruolo.getNome())).collect(Collectors.toList());
	}
	
	
	public boolean email_exists(UtenteRegistrationDto registrationDto) {
		if (userRepository.findByEmail(registrationDto.getMail()) != null) {
		return true;
		}
		return false;
	}
	
}