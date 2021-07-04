package com.sad.yardmanagementsystem.model;

import java.util.Collection;
import java.util.List;

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
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name =  "Deposito")
public class Deposito {
	
	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "indirizzo")
	private String indirizzo;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_gestore", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Utente gestore;
	
	@OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "id")
	@JoinColumn(name = "codice_deposito", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private List<Ordine> ordini;
	
	private List<DepositiCorrieri> corrieri;
	
	
	public Deposito() {
		super();
	}

	public Deposito(Long id, String indirizzo, Gestore gestore) {
		super();
		this.id = id;
		this.indirizzo = indirizzo;
		this.gestore = gestore;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIndirizzo() {
		return indirizzo;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	public Utente getGestore() {
		return gestore;
	}

	public void setGestore(Gestore gestore) {
		this.gestore = gestore;
	}

	public void setGestore(Utente gestore) {
		this.gestore = gestore;
	}
	
	
	
	
	

}
