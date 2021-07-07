package com.sad.yardmanagementsystem.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;


@Entity
@Table(name =  "Fornitore",uniqueConstraints = @UniqueConstraint(columnNames = "mail"))
public class Fornitore {

	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "mail")
	private String mail;
	
	@Column(name = "ragione_sociale")
	private String ragioneSociale;
	
	@Column(name = "indirizzo")
	private String indirizzo;
	
	@Column(name = "telefono")
	private String telefono;
	
	@OneToOne
	@MapsId
	@JoinColumn(name = "id")
	private OrdineScarico ordineScarico;
	
	public Fornitore(){
		super();
	}
	
	public Fornitore(Long id, String mail, String ragioneSociale, String indirizzo, String telefono, OrdineScarico ordineScarico) {
		super();
		this.id = id;
		this.mail = mail;
		this.ragioneSociale = ragioneSociale;
		this.indirizzo = indirizzo;
		this.telefono = telefono;
		this.ordineScarico = ordineScarico;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getMail() {
		return mail;
	}
	
	public void setMail(String mail) {
		this.mail = mail;
	}
	
	public String getRagioneSociale() {
		return ragioneSociale;
	}
	
	public void setRagioneSociale(String ragioneSociale) {
		this.ragioneSociale = ragioneSociale;
	}
	
	public String getIndirizzo() {
		return indirizzo;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}
	
	public String getTelefono() {
		return telefono;
	}
	
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
	public OrdineScarico getOrdineScarico() {
		return ordineScarico;
	}
	
	public void setOrdineScarico(OrdineScarico ordineScarico) {
		this.ordineScarico = ordineScarico;
	}
	
}

