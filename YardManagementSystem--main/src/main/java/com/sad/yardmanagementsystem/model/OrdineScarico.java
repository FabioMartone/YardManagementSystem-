package com.sad.yardmanagementsystem.model;

import java.util.Collection;
import java.util.List;

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
import javax.persistence.AttributeOverrides;
import javax.persistence.AttributeOverride;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name =  "OrdineScarico")
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
public class OrdineScarico extends Ordine {
	
		
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "codice_deposito", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
	private Long codiceDeposito;
	
	@OneToOne(cascade = CascadeType.ALL)
	    @JoinColumn(name = "prenotazione_ordine", referencedColumnName = "codice")
	    private Prenotazione prenotazione;

	@Column(name = "fornitore")
	private Fornitore fornitore;
	
	public OrdineScarico() {
		// TODO Auto-generated constructor stub
		super();
		fornitore = new Fornitore();
	}

	public OrdineScarico(Long numero, String chiave, String dataPrevista, int numeroColli, int numeroColonne,
			int numeroPedane, int pesoTotale,Long codiceDeposito, Prenotazione prenotazione, Fornitore fornitore) {
		super(numero, chiave, dataPrevista, numeroColli, numeroColonne, numeroPedane, pesoTotale,codiceDeposito, prenotazione);
		// TODO Auto-generated constructor stub
		this.fornitore = fornitore;
	}
	
	public Fornitore getFornitore() {
		return fornitore;
	}
	
	public void setFornitore(Fornitore fornitore) {
		this.fornitore = fornitore;
	}

}
