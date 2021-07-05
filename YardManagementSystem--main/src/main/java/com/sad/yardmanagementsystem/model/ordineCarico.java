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
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name =  "OrdineCarico")
@AttributeOverrides({
    @AttributeOverride(name="numero",
                       column=@Column(name="numero")),
    @AttributeOverride(name="chiave",
                       column=@Column(name="chiave")),
    @AttributeOverride(name="dataPrevista",
    			column=@Column(name="data_prevista")),
    @AttributeOverride(name="numeroColli",
				column=@Column(name="numero_colli")),
    @AttributeOverride(name="numeroColonne",
				column=@Column(name="numero_colonne")),
    @AttributeOverride(name="numeropPedane",
				column=@Column(name="numero_pedane")),
    @AttributeOverride(name="pesoTotale",
    			column=@Column(name="peso_totale")),

})
public class ordineCarico extends Ordine {
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "codice_deposito", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
	private Long codiceDeposito;
	
	@OneToOne(cascade = CascadeType.ALL)
	    @JoinColumn(name = "prenotazione_ordine", referencedColumnName = "codice")
	    private Prenotazione prenotazione;

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
			int numeroPedane, int pesoTotale, Long codiceDeposito, Prenotazione prenotazione, String dataMercePronta, String clienteDestinatario, String indirizzoDestinatario) {
		super(numero, chiave, dataPrevista, numeroColli, numeroColonne, numeroPedane, pesoTotale, codiceDeposito, prenotazione);
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


