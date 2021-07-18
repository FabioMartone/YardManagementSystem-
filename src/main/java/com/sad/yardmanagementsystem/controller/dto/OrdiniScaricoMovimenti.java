package com.sad.yardmanagementsystem.controller.dto;

import com.sad.yardmanagementsystem.model.OrdineScarico;
import com.sad.yardmanagementsystem.model.Movimento;
import java.util.List;

public class OrdiniScaricoMovimenti {
	
	public OrdiniScaricoMovimenti(OrdineScarico ordineScarico, List<Movimento> movimenti) {
		super();
		this.ordineScarico = ordineScarico;
		this.movimenti = movimenti;
	}

	private OrdineScarico ordineScarico;

	private List<Movimento> movimenti;
	
	public OrdiniScaricoMovimenti() {
		// TODO Auto-generated constructor stub
		super();
	}

	public OrdineScarico getOrdineScarico() {
		return ordineScarico;
	}

	public void setOrdineScarico(OrdineScarico ordineScarico) {
		this.ordineScarico = ordineScarico;
	}

	public List<Movimento> getMovimenti() {
		return movimenti;
	}

	public void setMovimenti(List<Movimento> movimenti) {
		this.movimenti = movimenti;
	}
	
	

}
