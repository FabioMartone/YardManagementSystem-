package com.sad.yardmanagementsystem.controller.dto;

public class UtenteRegistrationDto {


 private String ragioneSociale;
 private String partitaIVA;
 private String indirizzo;
 private String referente;
 private String mail;
 private String telefono;
 private String password;
 private String tipologia;
 
 
public UtenteRegistrationDto() {
	super();
}


public UtenteRegistrationDto(String ragioneSociale, String partitaIVA, String indirizzo, String referente, String mail,
		String telefono, String password, String tipologia) {
	super();
	this.ragioneSociale = ragioneSociale;
	this.partitaIVA = partitaIVA;
	this.indirizzo = indirizzo;
	this.referente = referente;
	this.mail = mail;
	this.telefono = telefono;
	this.password = password;
	this.tipologia = tipologia;
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


public String getIndirizzo() {
	return indirizzo;
}


public void setIndirizzo(String indirizzo) {
	this.indirizzo = indirizzo;
}


public String getReferente() {
	return referente;
}


public void setReferente(String referente) {
	this.referente = referente;
}


public String getMail() {
	return mail;
}


public void setMail(String mail) {
	this.mail = mail;
}


public String getTelefono() {
	return telefono;
}


public void setTelefono(String telefono) {
	this.telefono = telefono;
}


public String getPassword() {
	return password;
}


public void setPassword(String password) {
	this.password = password;
}


public String getTipologia() {
	return tipologia;
}


public void setTipologia(String tipologia) {
	this.tipologia = tipologia;
}

 
}