package com.sad.yardmanagementsystem.model;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;


@Entity
@Table(name =  "Ordine")
public class Ordine {
	
	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	private Long numero;
	
	@Column(name = "chiave")
	private String chiave;
	
	@Column(name = "data_prevista")
	private String dataPrevista;
	
	@Column(name = "numero_colli")
	private int numeroColli;
	
	@Column(name = "numero_colonne")
	private int numeroColonne;
	
	@Column(name = "numero_pedane")
	private int numeroPedane;

	@Column(name = "peso_totale")
	private int pesoTotale;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "codice_deposito", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
	private Long codiceDeposito;
	
	public Ordine() {
		// TODO Auto-generated constructor stub
		super();
	}

	public Ordine(Long numero, String chiave, String dataPrevista, int numeroColli, int numeroColonne, int numeroPedane, int pesoTotale, Long codiceDeposito) {
			super();
			this.numero = numero;
			this.chiave = chiave;
			this.dataPrevista = dataPrevista;
			this.numeroColli = numeroColli;
			this.numeroColonne = numeroColonne;
			this.numeroPedane = numeroPedane;
			this.codiceDeposito = codiceDeposito;
	}

	public Long getNumero() {
		return numero;
	}
	
	public void setNumero(Long numero) {
		this.numero = numero;
	}
	
	public String getChiave() {
		return chiave;
	}
	
	public void setChiave(String chiave) {
		this.chiave = chiave;
	}
	
	public String getDataPrevista() {
		return dataPrevista;
	}
	
	public void setDataPrevista(String DataPrevista) {
		this.dataPrevista = DataPrevista;
	}
	
	public int getNumeroColli() {
		return numeroColli;
	}
	
	public void setNumeroColli(int numeroColli) {
		this.numeroColli = numeroColli;
	}
	
	public int getNumeroColonne() {
		return numeroColonne;
	}
	
	public void setNumeroColonne(int numeroColonne) {
		this.numeroColonne = numeroColonne;
	}
	
	public int getNumeroPedane() {
		return numeroPedane;
	}
	
	public void setNumeroPedane(int numeroPedane) {
		this.numeroPedane = numeroPedane;
	}
	
	public Long getCodiceDeposito() {
		return codiceDeposito; 
	}
	
	public void setCodiceDeposito(Long codiceDeposito) {
		this.codiceDeposito = codiceDeposito;
	}
	
}


