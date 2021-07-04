package com.sad.yardmanagementsystem.model;

import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

public class Gestore extends Utente {
		
		
	@OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "id")
	
	private List<Deposito> depositi;

	
	public Gestore() {
		super();
	}

	public Gestore(List<Deposito> depositi) {
		super();
		this.depositi = depositi;
	}

	public List<Deposito> getDepositi() {
		return depositi;
	}

	public void setDepositi(List<Deposito> depositi) {
		this.depositi = depositi;
	}
	
	
}
