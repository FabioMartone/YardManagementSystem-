package com.sad.yardmanagementsystem.controller.dto;

public class ViewPrenotazioni {
	
	public ViewPrenotazioni(String data, String fasciaOraria, String indirizzo, String numero, String dataPrevista,
			int numeroColli, int numeroColonne, int numeroPedane, float pesoTotale) {
		super();
		this.data = data;
		this.fasciaOraria = fasciaOraria;
		this.indirizzo = indirizzo;
		this.numero = numero;
		this.dataPrevista = dataPrevista;
		this.numeroColli = numeroColli;
		this.numeroColonne = numeroColonne;
		this.numeroPedane = numeroPedane;
		this.pesoTotale = pesoTotale;
	}
	
	public ViewPrenotazioni(String data, String indirizzo, String numero,
			int numeroColli, int numeroColonne, int numeroPedane, float pesoTotale) {
		super();
		this.data = data;
		this.indirizzo=indirizzo;
		this.numero = numero;
		this.numeroColli = numeroColli;
		this.numeroColonne = numeroColonne;
		this.numeroPedane = numeroPedane;
		this.pesoTotale = pesoTotale;
	}
	
	public ViewPrenotazioni(String data, String fasciaOraria, String indirizzo) {
		super();
		this.data = data;
		this.fasciaOraria = fasciaOraria;
		this.indirizzo = indirizzo;
	}

	private String data;
	
	private String fasciaOraria;
	
	private String indirizzo;
	
	private String numero;
	
	private String dataPrevista;
	
	private int numeroColli;
	
	private int numeroColonne;
	
	private int numeroPedane;

	private float pesoTotale;

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getFasciaOraria() {
		return fasciaOraria;
	}

	public void setFasciaOraria(String fasciaOraria) {
		this.fasciaOraria = fasciaOraria;
	}

	public String getIndirizzo() {
		return indirizzo;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
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

	public void setDataPrevista(String dataPrevista) {
		this.dataPrevista = dataPrevista;
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

	public void setPesoTotale(float pesoTotale) {
		this.pesoTotale = pesoTotale;
	}
	

}
