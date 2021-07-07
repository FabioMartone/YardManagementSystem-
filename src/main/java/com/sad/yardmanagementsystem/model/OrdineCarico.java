package com.sad.yardmanagementsystem.model;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name =  "OrdineCarico", uniqueConstraints = @UniqueConstraint(columnNames = "chiave_ordine"))
public class OrdineCarico extends Ordine {
	
	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	private Long chiave;
	
	@Column(name = "chiave_ordine")
	private Long chiaveOrdine;
	
	@Column(name = "data_merce_pronta")
	private String dataMercePronta;
	
	@Column(name = "cliente_destinatario")
	private String clienteDestinatario;
	
	@Column(name = "indirizzo_destinatario")
	private String indirizzoDestinatario;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_deposito")
    @OnDelete(action = OnDeleteAction.CASCADE)
	private Deposito deposito;
	
	@OneToOne(mappedBy = "OrdineCarico",cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Prenotazione prenotazione;
	
	public OrdineCarico() {
		// TODO Auto-generated constructor stub
		super();
	}

	public OrdineCarico(Long numero, String dataPrevista, int numeroColli, int numeroColonne,
			int numeroPedane, int pesoTotale, Long chiave, Deposito deposito, String dataMercePronta, String clienteDestinatario, String indirizzoDestinatario, Prenotazione prenotazione) {
		super(numero, dataPrevista, numeroColli, numeroColonne, numeroPedane, pesoTotale);
		// TODO Auto-generated constructor stub
		this.dataMercePronta = dataMercePronta;
		this.clienteDestinatario = clienteDestinatario;
		this.indirizzoDestinatario = indirizzoDestinatario;
		this.chiave = chiave;
		this.deposito = deposito;
		this.prenotazione = prenotazione;
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

	public void setIndirizzoDestinatario(String indirizzoDestinatario) {
		this.indirizzoDestinatario = indirizzoDestinatario;
	}

	public Deposito getDeposito() {
		return deposito;
	}

	public void setDeposito(Deposito deposito) {
		this.deposito = deposito;
	}

	public Prenotazione getPrenotazione() {
		return prenotazione;
	}

	public void setPrenotazione(Prenotazione prenotazione) {
		this.prenotazione = prenotazione;
	}

	public Long getChiaveOrdine() {
		return chiaveOrdine;
	}

	public void setChiaveOrdine(Long chiaveOrdine) {
		this.chiaveOrdine = chiaveOrdine;
	}

	
}


