package com.sad.yardmanagementsystem.service;

import java.util.List;

import com.sad.yardmanagementsystem.controller.dto.CorrierePrenotazioneDto; 
import com.sad.yardmanagementsystem.model.Prenotazione;

public interface PrenotazioneService {

	Prenotazione createPrenotazione(CorrierePrenotazioneDto prenotazioneDto);
	boolean ordine_exists(CorrierePrenotazioneDto prenotazioneDto);
	boolean associationExists(CorrierePrenotazioneDto prenotazioneDto, Long id);
	boolean prenotazione_already_exists(CorrierePrenotazioneDto prenotazioneDto);
	boolean telefono_error_exists(String telefono);
	boolean email_error_exists(String email);
	List<Prenotazione> getPrenotazioniFromIdcorriere(Long id_corriere);
}
