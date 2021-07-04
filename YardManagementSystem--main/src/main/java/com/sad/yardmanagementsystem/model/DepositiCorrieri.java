package com.sad.yardmanagementsystem.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "depositi_corrieri")
public class DepositiCorrieri {
	
	
	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_corriere", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Utente corriere;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_deposito", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Deposito deposito;

	@Column(name = "stato")
	private String stato;
	
	
	
	public DepositiCorrieri() {
		super();
	}

	public DepositiCorrieri(Utente corriere, Deposito deposito, String stato) {
		super();
		this.corriere = corriere;
		this.deposito = deposito;
		this.stato = stato;
	}

	public Utente getCorriere() {
		return corriere;
	}

	public void setCorriere(Utente corriere) {
		this.corriere = corriere;
	}

	public Deposito getDeposito() {
		return deposito;
	}

	public void setDeposito(Deposito deposito) {
		this.deposito = deposito;
	}

	public String getStato() {
		return stato;
	}

	public void setStato(String stato) {
		this.stato = stato;
	}
	
	

}
