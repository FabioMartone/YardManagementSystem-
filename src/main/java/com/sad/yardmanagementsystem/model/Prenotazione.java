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
import javax.persistence.MapsId;
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
	@Column(name = "codice")
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
	
	@OneToOne
	@JoinColumn(name = "codice")
    private OrdineCarico OrdineCarico;
	
	@OneToOne
	@JoinColumn(name = "codice")
    private OrdineScarico ordineScarico;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_corriere", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Utente corriere;
	
	public Prenotazione() {
		// TODO Auto-generated constructor stub
		super();
	}
	
	public Prenotazione(String data, String fasciaOraria, OrdineCarico OrdineCarico,OrdineScarico ordineScarico, Utente corriere) {
		super();
		this.data = data;
		this.fasciaOraria = fasciaOraria;
		this.OrdineCarico = OrdineCarico;
		this.ordineScarico = ordineScarico;
		this.corriere = corriere;
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
	
	public Ordine getOrdineCarico() {
		return OrdineCarico;
	}
	
	public void setOrdineCarico(OrdineCarico OrdineCarico) {
		this.OrdineCarico = OrdineCarico;
	}

	public Ordine getOrdineScarico() {
		return ordineScarico;
	}
	
	public void setOrdineScarico(OrdineScarico ordineScarico) {
		this.ordineScarico = ordineScarico;
	}
	
	public Utente getCorriere() {
		return corriere;
	}
	
	public void setCorriere(Utente corriere) {
		this.corriere = corriere;
	}
	

}
