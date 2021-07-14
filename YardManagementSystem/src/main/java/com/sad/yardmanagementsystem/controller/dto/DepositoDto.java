package com.sad.yardmanagementsystem.controller.dto;

import java.util.List;


public class DepositoDto {
	
	private String indirizzo;
	private String descrizione;
	private String tipo;
	
	public DepositoDto(String indirizzo, String descrizione, int flag, String tipo, List<AreaDto> aree) {
		super();
		this.indirizzo = indirizzo;
		this.descrizione = descrizione;
		this.tipo = tipo;
	}

	public DepositoDto() {
		super();
	}
	
	public String getIndirizzo() {
		return indirizzo;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
}
