package com.sad.yardmanagementsystem.model;

import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.AttributeOverrides;
import javax.persistence.AttributeOverride;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name =  "OrdineScarico")

public class OrdineScarico extends Ordine {
	
	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	private Long chiave;
	
	@OneToOne(mappedBy = "ordineScarico",cascade = CascadeType.ALL)
	@PrimaryKeyJoinColumn
	private Fornitore fornitore;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "codice_deposito", nullable = false)
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

	public OrdineScarico(Long numero, Long chiave, String dataPrevista, int numeroColli, int numeroColonne,
			int numeroPedane, int pesoTotale,Long codiceDeposito, Fornitore fornitore) {
		super(numero, dataPrevista, numeroColli, numeroColonne, numeroPedane, pesoTotale,codiceDeposito);
		// TODO Auto-generated constructor stub
		this.fornitore = fornitore;
		this.chiave = chiave;
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

}
