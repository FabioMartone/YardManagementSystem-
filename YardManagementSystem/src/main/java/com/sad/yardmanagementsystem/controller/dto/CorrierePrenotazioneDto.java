package com.sad.yardmanagementsystem.controller.dto;

public class CorrierePrenotazioneDto {
	
	private String chiavePrenotazione;

	private String orario;
	
	private String nome;
	private String cognome;
	private String tipoDocumento;
	private String numeroDocumento;
	private String telefono;
	private String email;
	private String targa;
	private Long chiaveCarico;
	private Long chiaveScarico;
	private Long depositoId;
	
	public CorrierePrenotazioneDto() {
		super();
	}
	
	public CorrierePrenotazioneDto(String chiavePrenotazione, String orario, String nome, String cognome, String tipoDocumento, String numeroDocumento, String telefono,
			String email, String targa, Long chiaveCarico, Long chiaveScarico,Long depositoId) {
		super();
		this.chiavePrenotazione = chiavePrenotazione;
		this.orario = orario;
		this.nome = nome;
		this.cognome = cognome;
		this.tipoDocumento = tipoDocumento;
		this.numeroDocumento = numeroDocumento;
		this.telefono = telefono;
		this.email = email;
		this.targa = targa;
		this.chiaveCarico=chiaveCarico;
		this.chiaveScarico=chiaveScarico;
		this.depositoId=depositoId;
	}

	public String getChiavePrenotazione() {
		return chiavePrenotazione;
	}

	public void setChiavePrenotazione(String chiavePrenotazione) {
		this.chiavePrenotazione = chiavePrenotazione;
	}

	public String getOrario() {
		return orario;
	}

	public void setOrario(String orario) {
		this.orario = orario;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
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
		this.telefono = telefono;
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
		this.targa = targa;
	}

	public Long getChiaveCarico() {
		return chiaveCarico;
	}

	public void setChiaveCarico(Long chiaveCarico) {
		this.chiaveCarico = chiaveCarico;
	}

	public Long getChiaveScarico() {
		return chiaveScarico;
	}

	public void setChiaveScarico(Long chiaveScarico) {
		this.chiaveScarico = chiaveScarico;
	}

	public Long getDepositoId() {
		return depositoId;
	}

	public void setDepositoId(Long depositoId) {
		this.depositoId = depositoId;
	}
	
	

}
