package com.sad.yardmanagementsystem.model;

import javax.persistence.Column; 
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "movimento")

public class Movimento {
	
	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "inizio_movimento")
	private String inizioMovimento;
	
	@Column(name = "fine_movimento")
	private String fineMovimento;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_area", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Area area;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_prenotazione", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Prenotazione prenotazione;

	public Movimento() {
		// TODO Auto-generated constructor stub
		super();
	}
	
	public Movimento(String Inizio, String Fine,  Area area, Prenotazione prenotazione) {
		super();
		this.inizioMovimento =Inizio;
		this.fineMovimento = Fine;
		this.area=area;
		this.prenotazione = prenotazione;
	}
	
	public Prenotazione getPrenotazione() {
		return prenotazione;
	}

	public void setPrenotazione(Prenotazione prenotazione) {
		this.prenotazione = prenotazione;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}
	
	public void setid(Long id) {
		this.id = id;
	}
	
	
	
	public String getInizioMovimento() {
		return inizioMovimento;
	}

	public void setInizioMovimento(String inizioMovimento) {
		this.inizioMovimento = inizioMovimento;
	}

	public String getFineMovimento() {
		return fineMovimento;
	}

	public void setFineMovimento(String fineMovimento) {
		this.fineMovimento = fineMovimento;
	}

	public Area getArea() {
		return area;
	}
	
	public void setArea(Area area) {
		this.area = area;
	}	

}
