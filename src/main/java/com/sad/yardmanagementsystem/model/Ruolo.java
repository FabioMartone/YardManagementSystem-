package com.sad.yardmanagementsystem.model;

import javax.persistence.Entity; 
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "ruolo",uniqueConstraints = @UniqueConstraint(columnNames = "nome"))
public class Ruolo {
	
	@Id
	private Long id;
	private String nome;
	
	public Ruolo() {
		
	}
	
	public Ruolo(String nome) {
		super();
		this.nome = nome;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setName(String nome) {
		this.nome = nome;
	}
}

