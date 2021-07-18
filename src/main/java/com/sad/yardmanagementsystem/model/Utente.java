package com.sad.yardmanagementsystem.model;

import java.util.Collection;

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
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;


@Entity
@Table(name =  "Utente", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class Utente {
	
	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "ragione_sociale")
	private String ragioneSociale;
	
	@Column(name = "partita_iva")
	private String partitaIVA;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "telefono")
	private String telefono;

	@Column(name = "tipologia")
	private String tipologia;
	
	@Column(name = "referente")
	private String referente;
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(
			name = "ruoli_utente",
			joinColumns = @JoinColumn(
		            name = "id_utente", referencedColumnName = "id"),
			inverseJoinColumns = @JoinColumn(
				            name = "id_ruolo", referencedColumnName = "id"))
	
	private Collection<Ruolo> ruoli;
		
	public Utente() {
		super();
	}

	public Utente(String ragioneSociale, String partitaIVA, String email, String password, String telefono, String tipologia,String referente,Collection<Ruolo> ruoli) {
		super();
		this.ragioneSociale = ragioneSociale;
		this.partitaIVA = partitaIVA;
		this.email = email;
		this.password = password;
		this.telefono = telefono;
		this.tipologia = tipologia;
		this.referente = referente;
		this.ruoli = ruoli;
		
	}
	
	public Utente(Utente u) {
		super();
		this.ragioneSociale = u.getRagioneSociale();
		this.partitaIVA = u.getPartitaIVA();
		this.email = u.getEmail();
		this.password =u.getPassword();
		this.telefono = u.getTelefono();
		this.tipologia = u.getTipologia();
		this.referente = u.getReferente();
		this.ruoli = u.getRoles();
	}

	public String getTipologia() {
		return tipologia;
	}

	public void setTipologia(String tipologia) {
		this.tipologia = tipologia;
	}

	public String getReferente() {
		return referente;
	}

	public void setReferente(String referente) {
		this.referente = referente;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRagioneSociale() {
		return ragioneSociale;
	}

	public void setRagioneSociale(String ragioneSociale) {
		this.ragioneSociale = ragioneSociale;
	}

	public String getPartitaIVA() {
		return partitaIVA;
	}

	public void setPartitaIVA(String partitaIVA) {
		this.partitaIVA = partitaIVA;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
	public Collection<Ruolo> getRoles() {
		return ruoli;
	}
	public void setRoles(Collection<Ruolo> ruoli) {
		this.ruoli = ruoli;
	}
	

}