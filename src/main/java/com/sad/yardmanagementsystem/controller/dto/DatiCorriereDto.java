package com.sad.yardmanagementsystem.controller.dto;

public class DatiCorriereDto {
	
	public DatiCorriereDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public DatiCorriereDto(String nome, String cognome, String tipoDocumento, String numeroDocumento, String telefono,
			String email, String targa) {
		super();
		this.nome = nome;
		this.cognome = cognome;
		this.tipoDocumento = tipoDocumento;
		this.numeroDocumento = numeroDocumento;
		this.telefono = telefono;
		this.email = email;
		this.targa = targa;
	}
	
	
	private String nome;
	private String cognome;
	private String tipoDocumento;
	private String numeroDocumento;
	private String telefono;
	private String email;
	private String targa;
	
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		nome = nome;
	}
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		cognome = cognome;
	}
	public String getTipoDocumento() {
		return tipoDocumento;
	}
	public void setTipoDocumento(String tipoDocumento) {
		tipoDocumento = tipoDocumento;
	}
	public String getNumeroDocumento() {
		return numeroDocumento;
	}
	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		telefono = telefono;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTarga() {
		return targa;
	}
	public void setTarga(String targa) {
		targa = targa;
	}
	
	
}
