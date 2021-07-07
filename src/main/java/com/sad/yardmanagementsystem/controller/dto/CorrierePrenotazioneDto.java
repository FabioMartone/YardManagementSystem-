package com.sad.yardmanagementsystem.controller.dto;

public class CorrierePrenotazioneDto {
	
	private Long chiaveOrdine;

	private String orario;
	
	public CorrierePrenotazioneDto() {
		super();
	}
	
	public CorrierePrenotazioneDto(Long chiaveOrdine, String orario) {
		super();
		this.chiaveOrdine = chiaveOrdine;
		this.orario = orario;
	}

	public Long getChiaveOrdine() {
		return chiaveOrdine;
	}

	public void setChiaveOrdine(Long chiaveOrdine) {
		this.chiaveOrdine = chiaveOrdine;
	}

	public String getOrario() {
		return orario;
	}

	public void setOrario(String orario) {
		this.orario = orario;
	}
	
	

}
