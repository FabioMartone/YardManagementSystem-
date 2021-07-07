package com.sad.yardmanagementsystem.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sad.yardmanagementsystem.controller.dto.DepositoDto;
import com.sad.yardmanagementsystem.controller.dto.DepositoOrarioDto;
import com.sad.yardmanagementsystem.controller.dto.UtenteRegistrationDto;
import com.sad.yardmanagementsystem.model.Area;
import com.sad.yardmanagementsystem.model.Deposito;
import com.sad.yardmanagementsystem.model.OrarioDisponibile;
import com.sad.yardmanagementsystem.model.TipoArea;
import com.sad.yardmanagementsystem.model.Utente;
import com.sad.yardmanagementsystem.repository.RepositoryDeposito;
import com.sad.yardmanagementsystem.repository.RepositoryOrarioDisponibile;
import com.sad.yardmanagementsystem.repository.RepositoryUtente;

@Service
public class DepositoServiceImpl implements DepositoService{
	
	@Autowired
	private RepositoryUtente userRepository;

	@Autowired
	private RepositoryDeposito depositoRepository;
	
	@Autowired
	private RepositoryOrarioDisponibile orarioRepository;
	
	public DepositoServiceImpl() {
		// TODO Auto-generated constructor stub
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
		
		Area a = new Area(depositoDto.getDescrizione(), 0, t, d);
		
		List<Area> l = d.getAree();
		
		l.add(a);
		
		d.setAree(l);
		
		depositoRepository.save(d);
		
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
