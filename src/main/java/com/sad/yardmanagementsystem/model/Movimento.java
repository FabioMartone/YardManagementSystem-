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
	
	@Column(name = "ora_inizio")
	private int oraInizio;
	
	@Column(name = "ora_fine")
	private int oraFine;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_deposito", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Deposito deposito;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_area", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Area area;

	public Movimento() {
		// TODO Auto-generated constructor stub
		super();
	}
	
	public Movimento(Long id, int oraInizio, int oraFine, Deposito deposito, Area area) {
		super();
		this.id = id;
		this.oraInizio = oraInizio;
		this.oraFine = oraFine;
		this.deposito = deposito;
		this.area=area;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setid(Long id) {
		this.id = id;
	}
	
	public int getOraInizio() {
		return oraInizio;
	}
	
	public void setOraInizio(int oraInizio) {
		this.oraInizio = oraInizio;
	}
	
	public int getOraFine() {
		return oraFine;
	}
	
	public void setOraFine(int oraFine) {
		this.oraFine = oraFine;
	}
	
	public Deposito getDeposito() {
		return deposito;
	}
	
	public void setDeposito(Deposito deposito) {
		this.deposito = deposito;
	}
	
	public Area getArea() {
		return area;
	}
	
	public void setArea(Area area) {
		this.area = area;
	}

}
