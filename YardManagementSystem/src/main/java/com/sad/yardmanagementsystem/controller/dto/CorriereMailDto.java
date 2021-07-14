package com.sad.yardmanagementsystem.controller.dto;

public class CorriereMailDto {
	private Long corriere;
	private String chiave_prenotazione;
	
	
	
	
	
	public CorriereMailDto() {
		super();
	}
	public CorriereMailDto(Long id_corriere, String chiave_prenotazione) {
		super();
		this.corriere = id_corriere;
		this.chiave_prenotazione = chiave_prenotazione;
	}
	public Long getCorriere() {
		return corriere;
	}
	public void setCorriere(Long id_corriere) {
		this.corriere = id_corriere;
	}
	public String getChiave_prenotazione() {
		return chiave_prenotazione;
	}
	public void setChiave_prenotazione(String chiave_prenotazione) {
		this.chiave_prenotazione = chiave_prenotazione;
	}
	
	
}
