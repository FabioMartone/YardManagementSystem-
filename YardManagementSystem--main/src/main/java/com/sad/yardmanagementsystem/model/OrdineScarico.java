package com.sad.yardmanagementsystem.model;

import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name =  "OrdineScarico")
public class OrdineScarico extends Ordine {

	@Column(name = "fornitore")
	private Fornitore fornitore;
	
	public OrdineScarico() {
		// TODO Auto-generated constructor stub
		super();
		fornitore = new Fornitore();
	}

	public OrdineScarico(Long numero, String chiave, String dataPrevista, int numeroColli, int numeroColonne,
			int numeroPedane, int pesoTotale,Long codiceDeposito, Prenotazione prenotazione, Fornitore fornitore) {
		super(numero, chiave, dataPrevista, numeroColli, numeroColonne, numeroPedane, pesoTotale,codiceDeposito, prenotazione);
		// TODO Auto-generated constructor stub
		this.fornitore = fornitore;
	}
	
	public Fornitore getFornitore() {
		return fornitore;
	}
	
	public void setFornitore(Fornitore fornitore) {
		this.fornitore = fornitore;
	}

}
