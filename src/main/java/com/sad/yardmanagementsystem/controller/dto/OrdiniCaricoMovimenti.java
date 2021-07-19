package com.sad.yardmanagementsystem.controller.dto;

import com.sad.yardmanagementsystem.model.OrdineCarico;
import java.util.List;
import com.sad.yardmanagementsystem.model.Movimento;


public class OrdiniCaricoMovimenti {
	
	public OrdiniCaricoMovimenti(OrdineCarico ordineCarico, List<Movimento> movimenti) {
		super();
		this.ordineCarico = ordineCarico;
		this.movimenti = movimenti;
	}

	private OrdineCarico ordineCarico;
	
	private List<Movimento> movimenti;

	public OrdiniCaricoMovimenti() {
		// TODO Auto-generated constructor stub
	}

	public OrdineCarico getOrdineCarico() {
		return ordineCarico;
	}

	public void setOrdineCarico(OrdineCarico ordineCarico) {
		this.ordineCarico = ordineCarico;
	}

	public List<Movimento> getMovimenti() {
		return movimenti;
	}

	public void setMovimenti(List<Movimento> movimenti) {
		this.movimenti = movimenti;
	}

}
