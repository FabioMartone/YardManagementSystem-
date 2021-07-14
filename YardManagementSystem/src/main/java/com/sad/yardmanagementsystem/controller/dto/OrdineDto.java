package com.sad.yardmanagementsystem.controller.dto;

public class OrdineDto {

	
	private String numero;
	private String dataPrevista;
	private int numeroColli;
	private int numeroColonne;
	private int numeroPedane;
	private float pesoTotale;
	private Long deposito;
	
	public OrdineDto() {
		super();
	}

	public OrdineDto(String numero, String dataPrevista, int numeroColli, int numeroColonne, int numeroPedane,
			float pesoTotale, Long Deposito) {
		super();
		this.numero = numero;
		this.dataPrevista = dataPrevista;
		this.numeroColli = numeroColli;
		this.numeroColonne = numeroColonne;
		this.numeroPedane = numeroPedane;
		this.pesoTotale = pesoTotale;
		this.deposito = Deposito;
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

	public Long getDeposito() {
		return deposito;
	}

	public void setDeposito(Long deposito) {
		this.deposito = deposito;
	}
	
	

	
	
}
