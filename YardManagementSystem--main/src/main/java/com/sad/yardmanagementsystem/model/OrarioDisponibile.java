package com.sad.yardmanagementsystem.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name =  "Orari", uniqueConstraints = @UniqueConstraint(columnNames = "fascia_oraria"))
public class OrarioDisponibile {
	
	@Id
	@Column(name = "fascia_oraria")
	private String fasciaOraria;
	
	public OrarioDisponibile() {
		super();
	}
	
	public OrarioDisponibile(String fasciaOraria) {
		super();
		this.fasciaOraria = fasciaOraria;
	}

	public String getFasciaOraria() {
		return fasciaOraria;
	}

	public void setFasciaOraria(String fasciaOraria) {
		this.fasciaOraria = fasciaOraria;
	}

}
