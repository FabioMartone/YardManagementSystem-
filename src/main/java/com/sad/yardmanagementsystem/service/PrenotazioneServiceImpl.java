package com.sad.yardmanagementsystem.service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.sad.yardmanagementsystem.controller.dto.CorrierePrenotazioneDto;
import com.sad.yardmanagementsystem.controller.dto.DepositoDto;
import com.sad.yardmanagementsystem.model.Area;
import com.sad.yardmanagementsystem.model.DepositiCorrieri;
import com.sad.yardmanagementsystem.model.Deposito;
import com.sad.yardmanagementsystem.model.Ordine;
import com.sad.yardmanagementsystem.model.OrdineScarico;
import com.sad.yardmanagementsystem.model.Prenotazione;
import com.sad.yardmanagementsystem.model.Utente;
import com.sad.yardmanagementsystem.repository.RepositoryDepositiCorrieri;
import com.sad.yardmanagementsystem.repository.RepositoryDeposito;
import com.sad.yardmanagementsystem.repository.RepositoryOrarioDisponibile;
import com.sad.yardmanagementsystem.repository.RepositoryOrdineCarico;
import com.sad.yardmanagementsystem.repository.RepositoryOrdineScarico;
import com.sad.yardmanagementsystem.repository.RepositoryPrenotazione;
import com.sad.yardmanagementsystem.repository.RepositoryUtente;
import com.sad.yardmanagementsystem.model.OrdineCarico;

@Service
public class PrenotazioneServiceImpl implements PrenotazioneService{

	@Autowired
	private RepositoryOrdineCarico repOrdineCarico;
	
	@Autowired
	private RepositoryOrdineScarico repOrdineScarico;
	
	@Autowired
	private RepositoryUtente repUtente;
	
	@Autowired
	private RepositoryPrenotazione repPrenotazione;
	
	@Autowired
	private RepositoryDeposito repDeposito;
	
	@Autowired
	private RepositoryDepositiCorrieri repDepCorr;
	
	public PrenotazioneServiceImpl() {
		// TODO Auto-generated constructor stub
	}
	

	public	boolean ordine_exists(CorrierePrenotazioneDto prenotazioneDto) {
			
			OrdineCarico ordineCarico = repOrdineCarico.findByChiaveOrdine(prenotazioneDto.getChiaveOrdine());
			OrdineScarico ordineScarico = repOrdineScarico.findByChiaveOrdine(prenotazioneDto.getChiaveOrdine());
			
			if (ordineCarico==null && ordineScarico==null) {
				return false;
			}
			
			return true;
		}
		
	public Prenotazione createPrenotazione(CorrierePrenotazioneDto prenotazioneDto) {
		
				Authentication auth = SecurityContextHolder.getContext().getAuthentication();
				Utente corriere = repUtente.findByEmail(auth.getName());
			
				String data;
				
				OrdineCarico ordineCarico = repOrdineCarico.findByChiaveOrdine(prenotazioneDto.getChiaveOrdine());
				OrdineScarico ordineScarico = repOrdineScarico.findByChiaveOrdine(prenotazioneDto.getChiaveOrdine());
							
				if (ordineCarico==null) {
					data = ordineScarico.getDataPrevista();
				}
				else {
					data = ordineCarico.getDataPrevista();
	
				}
				
			Prenotazione prenotazione = new Prenotazione(data, null, ordineCarico, ordineScarico, corriere);
			
			return repPrenotazione.save(prenotazione);
			
		}
	
	
	public boolean associationExists(CorrierePrenotazioneDto prenotazioneDto, Long id_corriere) {
		
		Long id_deposito;
		
		OrdineCarico ordineCarico = repOrdineCarico.findByChiaveOrdine(prenotazioneDto.getChiaveOrdine());
		OrdineScarico ordineScarico = repOrdineScarico.findByChiaveOrdine(prenotazioneDto.getChiaveOrdine());
					
		if (ordineCarico==null) {
			id_deposito = ordineScarico.getDeposito().getId();
		}
		else {
			id_deposito = ordineCarico.getDeposito().getId();
		}
		
		if(repDepCorr.findByIdDepositoIdCorriere(id_deposito, id_corriere) != null) {
			return true;
		}
		
		return false;
		
	}
	
	public boolean prenotazione_already_exists(CorrierePrenotazioneDto prenotazioneDto) {
		
		OrdineCarico ordineCarico = repOrdineCarico.findByChiaveOrdine(prenotazioneDto.getChiaveOrdine());
		OrdineScarico ordineScarico = repOrdineScarico.findByChiaveOrdine(prenotazioneDto.getChiaveOrdine());
	
		if (ordineCarico==null) {
			if(repPrenotazione.findByOrdineScarico(ordineScarico.getChiave()) != null) {
				return true;
			}
		}	
		else {
			if(repPrenotazione.findByOrdineCarico(ordineCarico.getChiave()) != null) {
				return true;
			}
		}
		
		return false;
	}	
	
}
	
