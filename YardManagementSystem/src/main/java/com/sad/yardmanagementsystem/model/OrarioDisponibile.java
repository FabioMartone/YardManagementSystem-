package com.sad.yardmanagementsystem.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name =  "Orari", uniqueConstraints = @UniqueConstraint(columnNames = "fascia_oraria"))
public class OrarioDisponibile {
	
	@Id
	@Column(name = "fascia_oraria")
	private String fasciaOraria;
	
	@ManyToMany(fetch = FetchType.LAZY,
			cascade = {
			CascadeType.PERSIST,
			CascadeType.MERGE
			},
			mappedBy = "orariDisponibili")
			private List<Deposito> depositi;
	
	

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

	public List<Deposito> getDepositi() {
		return depositi;
	}

	public void setDepositi(List<Deposito> depositi) {
		this.depositi = depositi;
	}
	
	
	

}