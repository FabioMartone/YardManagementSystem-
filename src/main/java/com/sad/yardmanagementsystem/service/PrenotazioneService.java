package com.sad.yardmanagementsystem.service;

import com.sad.yardmanagementsystem.controller.dto.CorrierePrenotazioneDto;
import com.sad.yardmanagementsystem.controller.dto.DepositoOrarioDto;
import com.sad.yardmanagementsystem.model.Prenotazione;

public interface PrenotazioneService {

	Prenotazione createPrenotazione(CorrierePrenotazioneDto prenotazioneDto);
	boolean ordine_exists(CorrierePrenotazioneDto prenotazioneDto);
	boolean associationExists(CorrierePrenotazioneDto prenotazioneDto, Long id);
	
}
