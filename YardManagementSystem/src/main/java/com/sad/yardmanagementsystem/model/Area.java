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
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name =  "Aree", uniqueConstraints = @UniqueConstraint(columnNames = "codice"))
public class Area {

	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	private Long codice;
	
	@Column(name = "descrizione")
	private String descrizione;
	
	@Column(name = "flag")
	private int flag;
	
	@Column(name = "tipo_area")
	private TipoArea tipo;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_deposito")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Deposito deposito;
	
	public Area() {
		// TODO Auto-generated constructor stub
		super();
	}
	
	public Area(String descrizione, int flag, TipoArea tipo, Deposito deposito) {
		super();
		this.descrizione = descrizione;
		this.flag = flag;
		this.tipo = tipo;
		this.deposito = deposito;
	}

	public Long getCodice() {
		return codice;
	}
	
	public void setCodice(Long codice) {
		this.codice = codice;
	}
	
	public String getDescrizione() {
		return descrizione;
	}
	
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	
	public int getFlag() {
		return flag;
	}
	
	public void setflag(int flag) {
		this.flag = flag;
	}
	
	public TipoArea getTipo() {
		return tipo;
	}
	
	public void setTipo(TipoArea tipo) {
		this.tipo = tipo;
	}

	public Deposito getDeposito() {
		return deposito;
	}

	public void setDeposito(Deposito deposito) {
		this.deposito = deposito;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}
	
}
