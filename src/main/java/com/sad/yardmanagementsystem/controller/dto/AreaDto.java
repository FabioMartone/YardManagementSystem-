package com.sad.yardmanagementsystem.controller.dto; 

import com.sad.yardmanagementsystem.model.TipoArea;

public class AreaDto {

	public AreaDto(String descrizione, int flag, TipoArea tipo) {
		super();
		this.descrizione = descrizione;
		this.flag = flag;
		this.tipo = tipo;
	}
	private String descrizione;
	private int flag;
	private TipoArea tipo;
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
	public TipoArea getTipo() {
		return tipo;
	}
	public void setTipo(TipoArea tipo) {
		this.tipo = tipo;
	}
	
}
