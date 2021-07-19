package com.sad.yardmanagementsystem.model;

import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

@Entity
public class Gestore extends Utente {
	
	@OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "id")
	private List<Deposito> depositi;
	
	public Gestore() {
		super();
	}
	
	public Gestore(String ragioneSociale, String partitaIVA, String email, String password, String telefono, String tipologia,String referente,Collection<Ruolo> ruoli, List<Deposito> depositi) {
		super(ragioneSociale, partitaIVA, email, password, telefono, tipologia, referente, ruoli);
		this.depositi = depositi;
	}

	public Gestore(Utente u) {
		super(u);
	}
	
	public List<Deposito> getDepositi() {
		return depositi;
	}

	public void setDepositi(List<Deposito> depositi) {
		this.depositi = depositi;
	}
	
	
}
