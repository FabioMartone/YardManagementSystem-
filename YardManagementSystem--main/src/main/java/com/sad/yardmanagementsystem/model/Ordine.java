package com.sad.yardmanagementsystem.model;

import java.util.Collection;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

public class Ordine {
	
	private Long numero;
	
	private String dataPrevista;
	
	private int numeroColli;
	
	private int numeroColonne;
	
	private int numeroPedane;

	private int pesoTotale;
	
	public Ordine() {
		// TODO Auto-generated constructor stub
		super();
	}

	public Ordine(Long numero, String dataPrevista, int numeroColli, int numeroColonne, int numeroPedane, int pesoTotale) {
			super();
			this.numero = numero;
			this.dataPrevista = dataPrevista;
			this.numeroColli = numeroColli;
			this.numeroColonne = numeroColonne;
			this.numeroPedane = numeroPedane;
			
	}

	public Long getNumero() {
		return numero;
	}
	
	public void setNumero(Long numero) {
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
	
	public int getPesoTotale() {
		return pesoTotale;
	}
	
	public void setPesoTotale(int peso) {
		this.pesoTotale = peso;
	}
		

}