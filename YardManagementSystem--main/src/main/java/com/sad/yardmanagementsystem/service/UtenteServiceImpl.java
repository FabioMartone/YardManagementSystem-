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
	private RepositoryDeposito depositoRepository;
	
	@Autowired
	private RepositoryOrarioDisponibile orarioRepository;
	
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
		Utente user = new Utente(registrationDto.getRagioneSociale(),registrationDto.getPartitaIVA(),registrationDto.getMail(),passwordEncoder.encode(registrationDto.getPassword()),registrationDto.getTelefono(),registrationDto.getTipologia(),registrationDto.getReferente(),Arrays.asList(ruoloRepository.findByNome(registrationDto.getTipologia())), new ArrayList<>());
		return userRepository.save(user);
	}
	
	public void add_orario_disp(DepositoOrarioDto depositoDto) {
		
		Optional<Deposito> dep = depositoRepository.findById(depositoDto.getId());
		
		Deposito deposito = dep.get();
		
		Collection<OrarioDisponibile> orari = deposito.getOrariDisponibili();
		
		orari.add(new OrarioDisponibile(depositoDto.getorario()));
		
		deposito.setOrariDisponibili(orari);
		
		depositoRepository.save(deposito);
		
	}
	
	public void delete_orario_disp(DepositoOrarioDto depositoDto) {
		
		Optional<Deposito> dep = depositoRepository.findById(depositoDto.getId());
		
		Deposito deposito = dep.get();
		
		Collection<OrarioDisponibile> orari = deposito.getOrariDisponibili();
		
		orari.remove(new OrarioDisponibile(depositoDto.getorario()));
		
		deposito.setOrariDisponibili(orari);
		
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
	
	@Override
	public Utente loadUserByMail(String email) throws UsernameNotFoundException {
	
		Utente user = userRepository.findByEmail(email);
		if(user == null) {
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		return user;
	}
	
	public Deposito add_deposito(DepositoDto depositoDto, Utente gestore) {
		
		Deposito deposito = new Deposito(depositoDto.getIndirizzo(), gestore, new ArrayList<Area>());
		
		return depositoRepository.save(deposito);
		
	}
	
	public void add_area(DepositoDto depositoDto) {
		
		Deposito d= depositoRepository.findByIndirizzo(depositoDto.getIndirizzo());
		
		TipoArea t;
		
		if(depositoDto.getTipo().equals("LAVORO")) {
			t=TipoArea.LAVORO;
		}
		else {
			t=TipoArea.SOSTA;
		}
		
		Area a = new Area(depositoDto.getDescrizione(), 0, t);
		
		List<Area> l = d.getAree();
		
		l.add(a);
		
		d.setAree(l);
		
		depositoRepository.save(d);
		
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
	
	public boolean deposito_exists(DepositoOrarioDto depositoDto) {
		
		Optional<Deposito> dep = depositoRepository.findById(depositoDto.getId());
		
		if (dep.isPresent()) {
			return true;
		}
		return false;
	}
	
	public boolean orario_deposito_exists(DepositoOrarioDto depositoDto) {
		
		Optional<Deposito> dep = depositoRepository.findById(depositoDto.getId());

		Deposito deposito = dep.get();
		
		for(OrarioDisponibile orario : deposito.getOrariDisponibili()) {
			if(orario.getFasciaOraria().equals(depositoDto.getorario())) {
				return true;
			}
		}
		return false;
		
	}
	
	public boolean fascia_oraria_exists(DepositoOrarioDto depositoDto) {
		
		Optional<OrarioDisponibile> ora = orarioRepository.findByFasciaOraria(depositoDto.getorario());
		
		if (ora.isPresent()) {
			return true;
		}
		return false;
	}
	
	public boolean is_associated(String email, DepositoOrarioDto depositoDto) {
		
		Utente u = userRepository.findByEmail(email);
		
		Optional<Deposito> dep = depositoRepository.findById(depositoDto.getId());
		
		Deposito deposito = dep.get();
		
		if (u.getId().equals(deposito.getGestore().getId())) {
			return true;
		}
		return false;
		
	}
}