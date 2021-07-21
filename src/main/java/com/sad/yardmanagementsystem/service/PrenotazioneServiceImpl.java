package com.sad.yardmanagementsystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.sad.yardmanagementsystem.controller.dto.CorrierePrenotazioneDto;
import com.sad.yardmanagementsystem.model.OrdineScarico;
import com.sad.yardmanagementsystem.model.Prenotazione;
import com.sad.yardmanagementsystem.model.Utente;
import com.sad.yardmanagementsystem.repository.RepositoryDepositiCorrieri;
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
	private RepositoryDepositiCorrieri repDepCorr;
	
	public PrenotazioneServiceImpl() {
		// TODO Auto-generated constructor stub
	}
	

	public	boolean ordine_exists(CorrierePrenotazioneDto prenotazioneDto) {
			
			OrdineCarico ordineCarico = repOrdineCarico.findByChiavePrenotazione(prenotazioneDto.getChiavePrenotazione());
			OrdineScarico ordineScarico = repOrdineScarico.findByChiavePrenotazione(prenotazioneDto.getChiavePrenotazione());
			
			if (ordineCarico==null && ordineScarico==null) {
				return false;
			}
			
			return true;
		}
		
	public Prenotazione createPrenotazione(CorrierePrenotazioneDto prenotazioneDto) {
		
				Authentication auth = SecurityContextHolder.getContext().getAuthentication();
				Utente corriere = repUtente.findByEmail(auth.getName());
			
				String data;
				
				OrdineCarico ordineCarico = repOrdineCarico.findByChiavePrenotazione(prenotazioneDto.getChiavePrenotazione());
				OrdineScarico ordineScarico = repOrdineScarico.findByChiavePrenotazione(prenotazioneDto.getChiavePrenotazione());
							
				if (ordineCarico==null) {
					data = ordineScarico.getDataPrevista();
				}
				else {
					data = ordineCarico.getDataPrevista();
	
				}
				
			Prenotazione prenotazione = new Prenotazione(data, prenotazioneDto.getOrario(), ordineCarico, ordineScarico, corriere,
					prenotazioneDto.getNome(), prenotazioneDto.getCognome(), prenotazioneDto.getTipoDocumento(), 
					prenotazioneDto.getNumeroDocumento(), prenotazioneDto.getTelefono(), prenotazioneDto.getEmail(), prenotazioneDto.getTarga());
			
			return repPrenotazione.save(prenotazione);
			
		}
	
	
	public boolean associationExists(CorrierePrenotazioneDto prenotazioneDto, Long id_corriere) {
		
		Long id_deposito;
		
		OrdineCarico ordineCarico = repOrdineCarico.findByChiavePrenotazione(prenotazioneDto.getChiavePrenotazione());
		OrdineScarico ordineScarico = repOrdineScarico.findByChiavePrenotazione(prenotazioneDto.getChiavePrenotazione());
					
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
		
		OrdineCarico ordineCarico = repOrdineCarico.findByChiavePrenotazione(prenotazioneDto.getChiavePrenotazione());
		OrdineScarico ordineScarico = repOrdineScarico.findByChiavePrenotazione(prenotazioneDto.getChiavePrenotazione());
	
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
	
	public boolean telefono_error_exists(String telefono) {
		
		int ok_tel=1;
		for (int i = 0; i < telefono.length() && (ok_tel==1); i++) {
			if(!Character.isDigit(telefono.charAt(i))){
				ok_tel=0;
			}
		}
		if(ok_tel==0) {
			return true;
		}
		return false;
	}
	
	public boolean email_error_exists(String email) {
		
		int ok_mail=0;
		int j=0;
		while (j != email.length() && email.charAt(j) != '@') {
			j = j + 1;
		}
		if (j != email.length()) {
			ok_mail = 1;
		}
		while(j != email.length() && email.charAt(j) != '.') {
			j = j + 1;
		}
		if (j == email.length()) {
			ok_mail = 0;
		}
		if (ok_mail == 0) {
			return true;
		}
		return false;
	}
	
	public List<Prenotazione> getPrenotazioniFromIdcorriere(Long id_corriere){
		
		List<Prenotazione> pren = repPrenotazione.findByIdCorriere(id_corriere);
		
		return pren;
	}
	
}
	
