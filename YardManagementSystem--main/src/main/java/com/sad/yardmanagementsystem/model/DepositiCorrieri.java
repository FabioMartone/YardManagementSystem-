package com.sad.yardmanagementsystem.model;

import java.util.List;

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
import javax.persistence.Table;
import java.util.List;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "depositi_corrieri")
public class DepositiCorrieri {
	
	
	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_corriere", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Utente corriere;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_deposito", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Deposito deposito;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(
			name = "corriere_prenotazione",
			joinColumns = @JoinColumn(
		            name = "id_corriere", referencedColumnName = "id"),
			inverseJoinColumns = @JoinColumn(
				            name = "codice_prenotazione", referencedColumnName = "codice"))
	private List<Prenotazione> prenotazioni;
	
	@Column(name = "stato")
	private String stato;
	
	
	
	public DepositiCorrieri() {
		super();
	}

	public DepositiCorrieri(Utente corriere, Deposito deposito, String stato, List<Prenotazione> prenotazioni) {
		super();
		this.corriere = corriere;
		this.deposito = deposito;
		this.stato = stato;
		this.prenotazioni = prenotazioni;
	}

	public Utente getCorriere() {
		return corriere;
	}

	public void setCorriere(Utente corriere) {
		this.corriere = corriere;
	}

	public Deposito getDeposito() {
		return deposito;
	}

	public void setDeposito(Deposito deposito) {
		this.deposito = deposito;
	}

	public String getStato() {
		return stato;
	}

	public void setStato(String stato) {
		this.stato = stato;
	}
	
	public List<Prenotazione> getPrenotazioni(){
		return prenotazioni;
	}
	
	public void setPrenotazioni(List<Prenotazione> prenotazioni) {
		this.prenotazioni = prenotazioni;
	}

}
