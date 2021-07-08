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
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name =  "Deposito", uniqueConstraints = @UniqueConstraint(columnNames = "indirizzo"))
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
	private List<OrdineCarico> OrdiniCarico;
	
	@OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "codice")
	private List<Area> aree;
	
	@OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "id")
	private List<DepositiCorrieri> corrieri;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(
			name = "orari_deposito",
			joinColumns = @JoinColumn(
		            name = "id_deposito", referencedColumnName = "id"),
			inverseJoinColumns = @JoinColumn(
				            name = "fascia_oraria", referencedColumnName = "fascia_oraria"))
	private Collection<OrarioDisponibile> orariDisponibili;
	
	public Deposito() {
		super();
	}

	public Deposito(String indirizzo, Utente gestore, List<Area> aree) {
		super();
		this.indirizzo = indirizzo;
		this.gestore = gestore;
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

	public void setGestore(Utente gestore) {
		this.gestore = gestore;
	}

	public List<OrdineScarico> getOrdiniScarico() {
		return ordiniScarico;
	}

	public void setOrdiniScarico(List<OrdineScarico> ordiniScarico) {
		this.ordiniScarico = ordiniScarico;
	}

	public List<OrdineCarico> getOrdiniCarico() {
		return OrdiniCarico;
	}

	public void setOrdiniCarico(List<OrdineCarico> ordiniCarico) {
		OrdiniCarico = ordiniCarico;
	}

	public List<Area> getAree() {
		return aree;
	}

	public void setAree(List<Area> aree) {
		this.aree = aree;
	}

	public List<DepositiCorrieri> getCorrieri() {
		return corrieri;
	}

	public void setCorrieri(List<DepositiCorrieri> corrieri) {
		this.corrieri = corrieri;
	}

	public Collection<OrarioDisponibile> getOrariDisponibili() {
		return orariDisponibili;
	}

	public void setOrariDisponibili(Collection<OrarioDisponibile> orariDisponibili) {
		this.orariDisponibili = orariDisponibili;
	}

	
}
