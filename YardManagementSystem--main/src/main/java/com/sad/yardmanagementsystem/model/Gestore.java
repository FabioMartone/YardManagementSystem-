package com.sad.yardmanagementsystem.model;

import java.util.Collection;
import java.util.List;

import javax.persistence.Entity;

@Entity
public class Gestore extends Utente {
	
	public Gestore() {
		super();
	}
	
	public Gestore(String ragioneSociale, String partitaIVA, String email, String password, String telefono, String tipologia,String referente,Collection<Ruolo> ruoli, List<Deposito> depositi) {
		super(ragioneSociale, partitaIVA, email, password, telefono, tipologia, referente, ruoli, depositi);
	}

	public Gestore(Utente u) {
		super(u);
	}
	
}
