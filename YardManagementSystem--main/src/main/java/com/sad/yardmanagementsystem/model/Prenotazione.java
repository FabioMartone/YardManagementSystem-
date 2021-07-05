package com.sad.yardmanagementsystem.model;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.List;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "Prenotazioni")

public class Prenotazione {

	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	private Long codice;
	
	@Column(name = "data")
	private String data;
	
	@Column(name = "fascia_oraria")
	private String fasciaOraria;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(
			name = "movimenti_prenotazione",
			joinColumns = @JoinColumn(
		            name = "codice_prenotazione", referencedColumnName = "codice"),
			inverseJoinColumns = @JoinColumn(
				            name = "id_movimento", referencedColumnName = "id"))
	private List<Movimento> movimenti;
	
	@OneToOne(mappedBy = "Prenotazioni")
    private Ordine ordine;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "numero_deposito_corriere", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private DepositiCorrieri depositiCorrieri;
	
	public Prenotazione() {
		// TODO Auto-generated constructor stub
		super();
	}
	
	public Prenotazione(Long codice, String data, String fasciaOraria, List<Movimento> movimenti, Ordine ordine, DepositiCorrieri depositiCorrieri) {
		super();
		this.codice = codice;
		this.data = data;
		this.fasciaOraria = fasciaOraria;
		this.movimenti = movimenti;
		this.ordine = ordine;
		this.depositiCorrieri = depositiCorrieri;
	}
	
	public Long getCodice() {
		return codice;
	}
	
	public void setCodice(Long codice) {
		this.codice = codice;
	}
	
	public String getData() {
		return data;
	}
	
	public void setData(String data) {
		this.data = data;
	}
	
	public String getFasciaOraria() {
		return fasciaOraria;
	}
	
	public void setFasciaOraria(String fasciaOraria) {
		this.fasciaOraria = fasciaOraria;
	}
	
	public List<Movimento> getMovimenti(){
		return movimenti;
	}
	
	public void setMovimenti(List<Movimento> movimenti) {
		this.movimenti = movimenti;
	}
	
	public Ordine getOrdine() {
		return ordine;
	}
	
	public void setOrdine(Ordine ordine) {
		this.ordine = ordine;
	}
	
	public DepositiCorrieri getDepositiCorrieri() {
		return depositiCorrieri;
	}
	
	public void setDepositiCorrieri(DepositiCorrieri depositiCorrieri) {
		this.depositiCorrieri = depositiCorrieri;
	}
	

}
