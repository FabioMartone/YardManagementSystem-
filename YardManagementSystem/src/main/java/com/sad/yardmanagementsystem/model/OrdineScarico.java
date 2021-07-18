package com.sad.yardmanagementsystem.model;


import javax.persistence.CascadeType;  
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name =  "OrdineScarico")

public class OrdineScarico extends Ordine {
	
	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	private Long chiave;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "forn_id", referencedColumnName = "id")
	private Fornitore fornitore;
	
	@OneToOne(mappedBy = "ordineScarico",cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Prenotazione prenotazione;
	
	public OrdineScarico() {
		// TODO Auto-generated constructor stub
		super();
		fornitore = new Fornitore();
	}

	public OrdineScarico(String numero, String dataPrevista, int numeroColli, int numeroColonne,
			int numeroPedane, float pesoTotale,String chiavePrenotazione, Deposito deposito, Fornitore fornitore, Prenotazione prenotazione) {
		super(numero, dataPrevista, numeroColli, numeroColonne, numeroPedane, pesoTotale, chiavePrenotazione, deposito);
		// TODO Auto-generated constructor stub
		this.fornitore = fornitore;
		this.prenotazione = prenotazione;
	}
	
	public Long getChiave() {
		return chiave;
	}
	
	public void setChiave(Long chiave) {
		this.chiave = chiave;
	}
	
	public Fornitore getFornitore() {
		return fornitore;
	}
	
	public void setFornitore(Fornitore fornitore) {
		this.fornitore = fornitore;
	}

	public Prenotazione getPrenotazione() {
		return prenotazione;
	}

	public void setPrenotazione(Prenotazione prenotazione) {
		this.prenotazione = prenotazione;
	}

}
