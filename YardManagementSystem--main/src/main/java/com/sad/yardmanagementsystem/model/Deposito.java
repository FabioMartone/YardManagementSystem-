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
            mappedBy = "deposito")
	private List<OrdineScarico> ordiniScarico;
	
	@OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "deposito")
	private List<ordineCarico> OrdiniCarico;
	
	@OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "codice")
	private List<Area> aree;
	
	@OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "id")
	private List<DepositiCorrieri> corrieri;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(
			name = "orari_deposito",
			joinColumns = @JoinColumn(
		            name = "id_deposito", referencedColumnName = "id"),
			inverseJoinColumns = @JoinColumn(
				            name = "orario_disponibile", referencedColumnName = "fascia_oraria"))
	private Collection<OrarioDisponibile> orariDisponibili;
	
	public Deposito() {
		super();
	}

	public Deposito(Long id, String indirizzo, Gestore gestore, Collection<OrarioDisponibile> orariDisponibili, List<DepositiCorrieri> corrieri, List<Area> Aree) {
		super();
		this.id = id;
		this.indirizzo = indirizzo;
		this.gestore = gestore;
		this.orariDisponibili = orariDisponibili;
		this.corrieri = corrieri;
		this.aree = aree;
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
	
	public Collection<OrarioDisponibile> getOrariDisponibili() {
		return orariDisponibili;
	}

	public void setOrariDisponibili(Collection<OrarioDisponibile> orari) {
		this.orariDisponibili = orari;
	}
	
	public List<DepositiCorrieri> getCorrieri(){
		return corrieri;
	}
	
	public void setCorrieri(List<DepositiCorrieri> corrieri) {
		this.corrieri = corrieri;
	}
	
	public List<Area> getAree(){
		return aree;
	}
	
	public void setAree(List<Area> aree) {
		this.aree = aree;
	}
	
	
	
	
	
	

}
