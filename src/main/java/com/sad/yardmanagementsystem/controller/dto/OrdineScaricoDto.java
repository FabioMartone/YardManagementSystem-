package com.sad.yardmanagementsystem.controller.dto;

public class OrdineScaricoDto extends OrdineDto {

	
	private String mailFornitore;
	
	
	private String ragioneSocialeFornitore;
	
	
	private String indirizzoFornitore;
	
	private String telefonoFornitore;
	
	

	public OrdineScaricoDto() {
		super();
		// TODO Auto-generated constructor stub
	}



	public OrdineScaricoDto(String numero, String dataPrevista, int numeroColli, int numeroColonne, int numeroPedane,
			float pesoTotale, Long deposito, String mailFornitore, String ragioneSocialeFornitore, String indirizzoFornitore,
			String telefonoFornitore) {
		super(numero, dataPrevista, numeroColli, numeroColonne, numeroPedane, pesoTotale, deposito);
		this.mailFornitore = mailFornitore;
		this.ragioneSocialeFornitore = ragioneSocialeFornitore;
		this.indirizzoFornitore = indirizzoFornitore;
		this.telefonoFornitore = telefonoFornitore;
		
	}



	public String getMailFornitore() {
		return mailFornitore;
	}



	public void setMailFornitore(String mailFornitore) {
		this.mailFornitore = mailFornitore;
	}



	public String getRagioneSocialeFornitore() {
		return ragioneSocialeFornitore;
	}



	public void setRagioneSocialeFornitore(String ragioneSocialeFornitore) {
		this.ragioneSocialeFornitore = ragioneSocialeFornitore;
	}



	public String getIndirizzoFornitore() {
		return indirizzoFornitore;
	}



	public void setIndirizzoFornitore(String indirizzoFornitore) {
		this.indirizzoFornitore = indirizzoFornitore;
	}



	public String getTelefonoFornitore() {
		return telefonoFornitore;
	}



	public void setTelefonoFornitore(String telefonoFornitore) {
		this.telefonoFornitore = telefonoFornitore;
	}




	
	
	
}
