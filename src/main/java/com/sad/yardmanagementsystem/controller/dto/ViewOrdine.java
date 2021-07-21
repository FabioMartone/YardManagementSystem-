package com.sad.yardmanagementsystem.controller.dto;

public class ViewOrdine {

	private String tipologiaOrdine;
	private String numeroOrdine;
	private String dataPrevista;
	private int numeroColli;
	private int numeroColonne;
	private int numeroPedane;
	private float pesoTotale;
	private String deposito;
	private String targa;
	private String autista;
	
	public ViewOrdine() {
		super();
	}

	public ViewOrdine(String tipologiaOrdine, String numeroOrdine, String dataPrevista, int numeroColli,
			int numeroColonne, int numeroPedane, float pesoTotale, String deposito, String targa,
			String autista) {
		super();
		this.tipologiaOrdine = tipologiaOrdine;
		this.numeroOrdine = numeroOrdine;
		this.dataPrevista = dataPrevista;
		this.numeroColli = numeroColli;
		this.numeroColonne = numeroColonne;
		this.numeroPedane = numeroPedane;
		this.pesoTotale = pesoTotale;
		this.deposito = deposito;
		this.targa = targa;
		this.autista = autista;
	}

	public String getTipologiaOrdine() {
		return tipologiaOrdine;
	}

	public void setTipologiaOrdine(String tipologiaOrdine) {
		this.tipologiaOrdine = tipologiaOrdine;
	}

	public String getNumeroOrdine() {
		return numeroOrdine;
	}

	public void setNumeroOrdine(String numeroOrdine) {
		this.numeroOrdine = numeroOrdine;
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

	public String getDeposito() {
		return deposito;
	}

	public void setDeposito(String deposito) {
		this.deposito = deposito;
	}

	public String getTarga() {
		return targa;
	}

	public void setTarga(String targa) {
		this.targa = targa;
	}

	public String getAutista() {
		return autista;
	}

	public void setAutista(String autista) {
		this.autista = autista;
	}
	
	
}
