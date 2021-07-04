package com.sad.yardmanagementsystem.model;

import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name =  "OrdineCarico")
public class ordineCarico extends Ordine {

	@Column(name = "data_merce_pronta")
	private String dataMercePronta;
	
	@Column(name = "cliente_destinatario")
	private String clienteDestinatario;
	
	@Column(name = "indirizzo_destinatario")
	private String indirizzoDestinatario;
	
	
	
	public ordineCarico() {
		// TODO Auto-generated constructor stub
		super();
	}

	public ordineCarico(Long numero, String chiave, String dataPrevista, int numeroColli, int numeroColonne,
			int numeroPedane, int pesoTotale, Long codiceDeposito, String dataMercePronta, String clienteDestinatario, String indirizzoDestinatario) {
		super(numero, chiave, dataPrevista, numeroColli, numeroColonne, numeroPedane, pesoTotale, codiceDeposito);
		// TODO Auto-generated constructor stub
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
	
	public void setIndirizzoDestinatario() {
		this.indirizzoDestinatario = indirizzoDestinatario;
	}
	
}


