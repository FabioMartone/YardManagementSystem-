package com.sad.yardmanagementsystem.model;

import java.util.Collection; 
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

public class Corriere extends Utente {
	public Corriere(List<DepositiCorrieri> depositi, Prenotazione prenotazione) {
		super();
		this.depositi = depositi;
		this.prenotazione = prenotazione;
	}

	public Corriere(String ragioneSociale, String partitaIVA, String email, String password, String telefono,
			String tipologia, String referente, Collection<Ruolo> ruoli, List<DepositiCorrieri> depositi, Prenotazione prenotazione) {
		super(ragioneSociale, partitaIVA, email, password, telefono, tipologia, referente, ruoli);
		// TODO Auto-generated constructor stub
		this.depositi = depositi;
		this.prenotazione = prenotazione;
	}

	public Corriere(Utente u) {
		super(u);
		// TODO Auto-generated constructor stub
	}

	@OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "id")
	private List<DepositiCorrieri> depositi;

	@OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "codice")
	private Prenotazione prenotazione;
	
	public Corriere() {
		super();
	}

	public List<DepositiCorrieri> getDepositi() {
		return depositi;
	}

	public void setDepositi(List<DepositiCorrieri> depositi) {
		this.depositi = depositi;
	}

	public Prenotazione getPrenotazione() {
		return prenotazione;
	}

	public void setPrenotazione(Prenotazione prenotazione) {
		this.prenotazione = prenotazione;
	}

	
	
}
