package com.sad.yardmanagementsystem.model;

import java.util.Collection;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name =  "OrdineCarico")
public class ordineCarico extends Ordine {
	
	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	private Long chiave;
	
	@Column(name = "data_merce_pronta")
	private String dataMercePronta;
	
	@Column(name = "cliente_destinatario")
	private String clienteDestinatario;
	
	@Column(name = "indirizzo_destinatario")
	private String indirizzoDestinatario;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "codice_deposito", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
	private Deposito deposito;
	
	@OneToOne(mappedBy = "OrdineCarico",cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Prenotazione prenotazione;
	
	public ordineCarico() {
		// TODO Auto-generated constructor stub
		super();
	}

	public ordineCarico(Long numero, Long chiave, String dataPrevista, int numeroColli, int numeroColonne,
			int numeroPedane, int pesoTotale, Long codiceDeposito, String dataMercePronta, String clienteDestinatario, String indirizzoDestinatario) {
		super(numero, dataPrevista, numeroColli, numeroColonne, numeroPedane, pesoTotale, codiceDeposito);
		// TODO Auto-generated constructor stub
		this.dataMercePronta = dataMercePronta;
		this.clienteDestinatario = clienteDestinatario;
		this.indirizzoDestinatario = indirizzoDestinatario;
		this.chiave = chiave;
	}

	public Long getChiave() {
		return chiave;
	}
	
	public void setChiave(Long chiave) {
		this.chiave = chiave;
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


