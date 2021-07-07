package com.sad.yardmanagementsystem.model;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name =  "OrdineScarico", uniqueConstraints = @UniqueConstraint(columnNames = "chiave_ordine"))

public class OrdineScarico extends Ordine {
	
	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	private Long chiave;
	
	@Column(name = "chiave_ordine")
	private Long chiaveOrdine;
	
	@OneToOne(mappedBy = "ordineScarico",cascade = CascadeType.ALL)
	@PrimaryKeyJoinColumn
	private Fornitore fornitore;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id")
    @OnDelete(action = OnDeleteAction.CASCADE)
	private Deposito deposito;
	
	@OneToOne(mappedBy = "ordineScarico",cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Prenotazione prenotazione;
	
	public OrdineScarico() {
		// TODO Auto-generated constructor stub
		super();
		fornitore = new Fornitore();
	}

	public OrdineScarico(Long numero, String dataPrevista, int numeroColli, int numeroColonne,
			int numeroPedane, int pesoTotale,Long chiave,Deposito deposito, Fornitore fornitore, Prenotazione prenotazione) {
		super(numero, dataPrevista, numeroColli, numeroColonne, numeroPedane, pesoTotale);
		// TODO Auto-generated constructor stub
		this.fornitore = fornitore;
		this.chiave = chiave;
		this.deposito = deposito;
		this.prenotazione = prenotazione;
	}
	
	public Long getChiave() {
		return chiave;
	}
	
	public void setChiave(Long chiave) {
		this.chiave = chiave;
	}
	
	public Fornitore getFornitore() {
		return fornitore;
	}
	
	public void setFornitore(Fornitore fornitore) {
		this.fornitore = fornitore;
	}
	

	public Deposito getDeposito() {
		return deposito;
	}

	public void setDeposito(Deposito deposito) {
		this.deposito = deposito;
	}

	public Prenotazione getPrenotazione() {
		return prenotazione;
	}

	public void setPrenotazione(Prenotazione prenotazione) {
		this.prenotazione = prenotazione;
	}

	public Long getChiaveOrdine() {
		return chiaveOrdine;
	}

	public void setChiaveOrdine(Long chiaveOrdine) {
		this.chiaveOrdine = chiaveOrdine;
	}

}
