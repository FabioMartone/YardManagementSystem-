package com.sad.yardmanagementsystem.model;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@MappedSuperclass
public class Ordine {
	
	private String numero;
	
	private String dataPrevista;
	
	private int numeroColli;
	
	private int numeroColonne;
	
	private int numeroPedane;

	private float pesoTotale;
	
	private String chiavePrenotazione;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id")
    @OnDelete(action = OnDeleteAction.CASCADE)
	private Deposito deposito;
	
	public Ordine() {
		// TODO Auto-generated constructor stub
		super();
	}

	public Ordine(String numero, String dataPrevista, int numeroColli, int numeroColonne, int numeroPedane, float pesoTotale, String chiavePrenotazione, Deposito deposito) {
			super();
			this.numero = numero;
			this.dataPrevista = dataPrevista;
			this.numeroColli = numeroColli;
			this.numeroColonne = numeroColonne;
			this.numeroPedane = numeroPedane;
			this.chiavePrenotazione = chiavePrenotazione;
			this.deposito=deposito;
			
	}

	public String getNumero() {
		return numero;
	}
	
	public void setNumero(String numero) {
		this.numero = numero;
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
	
	public float getPesoTotale() {
		return pesoTotale;
	}
	
	public void setPesoTotale(float peso) {
		this.pesoTotale = peso;
	}

	public String getChiavePrenotazione() {
		return chiavePrenotazione;
	}

	public void setChiavePrenotazione(String chiavePrenotazione) {
		this.chiavePrenotazione = chiavePrenotazione;
	}

	public Deposito getDeposito() {
		return deposito;
	}

	public void setDeposito(Deposito deposito) {
		this.deposito = deposito;
	}
	
	
		

}