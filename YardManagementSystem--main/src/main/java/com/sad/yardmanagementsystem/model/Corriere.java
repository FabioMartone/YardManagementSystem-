package com.sad.yardmanagementsystem.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

public class Corriere extends Utente {
	@OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "id")
	
	private List<DepositiCorrieri> depositi;

	
	public Corriere() {
		super();
	}

	
	
}