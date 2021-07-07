package com.sad.yardmanagementsystem.model;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import antlr.collections.List;

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