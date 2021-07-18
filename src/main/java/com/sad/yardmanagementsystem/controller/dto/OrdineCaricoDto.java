package com.sad.yardmanagementsystem.controller.dto;

public class OrdineCaricoDto extends OrdineDto {

	private String dataMercePronta;
	private String clienteDestinatario;
	private String indirizzoDestinatario;
		
	
	public OrdineCaricoDto() {
		super();
	}


	public OrdineCaricoDto(String numero, String dataPrevista, int numeroColli, int numeroColonne, int numeroPedane,
			float pesoTotale, Long deposito, String dataMercePronta, String clienteDestinatario, String indirizzoDestinatario) {
		super(numero, dataPrevista, numeroColli, numeroColonne, numeroPedane, pesoTotale, deposito);
		
		this.dataMercePronta = dataMercePronta;
		this.clienteDestinatario = clienteDestinatario;
		this.indirizzoDestinatario = indirizzoDestinatario;
	}

	public String getDataMercePronta() {
		return dataMercePronta;
	}




	public void setDataMercePronta(String dataMercePronta) {
		this.dataMercePronta = dataMercePronta;
	}




	public String getClienteDestinatario() {
		return clienteDestinatario;
	}




	public void setClienteDestinatario(String clienteDestinatario) {
		this.clienteDestinatario = clienteDestinatario;
	}




	public String getIndirizzoDestinatario() {
		return indirizzoDestinatario;
	}




	public void setIndirizzoDestinatario(String indirizzoDestinatario) {
		this.indirizzoDestinatario = indirizzoDestinatario;
	}	
	
}
