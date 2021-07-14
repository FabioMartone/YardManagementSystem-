package com.sad.yardmanagementsystem.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.List;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
 
@Entity
@Table(name = "Prenotazioni")

public class Prenotazione {

	@Id
	@Column(name = "codice")
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	private Long codice;
	
	@Column(name = "data")
	private String data;
	
	@Column(name = "fascia_oraria")
	private String fasciaOraria;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(
			name = "movimenti_prenotazione",
			joinColumns = @JoinColumn(
		            name = "codice_prenotazione", referencedColumnName = "codice"),
			inverseJoinColumns = @JoinColumn(
				            name = "id_movimento", referencedColumnName = "id"))
	private List<Movimento> movimenti;
	
	@OneToOne
	@JoinColumn(name = "codice_ordine_carico")
    private OrdineCarico OrdineCarico;
	
	@OneToOne
	@JoinColumn(name = "codice_ordine_scarico")
    private OrdineScarico ordineScarico;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_corriere", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Utente corriere;
	
	@Column(name = "nome_corriere")
	private String nomeCorriere;
	
	@Column(name = "cognome_corriere")
	private String cognomeCorriere;
	
	@Column(name = "tipo_documento_corriere")
	private String tipoDocumentoCorriere;
	
	@Column(name = "numero_documento_corriere")
	private String numeroDocumentoCorriere;
	
	@Column(name = "telefono_corriere")
	private String telefonoCorriere;
	
	@Column(name = "email_corriere")
	private String emailCorriere;
	
	@Column(name = "targa_corriere")
	private String targaCorriere;
	
	public Prenotazione() {
		// TODO Auto-generated constructor stub
		super();
	}
	
	public Prenotazione(String data, String fasciaOraria, OrdineCarico OrdineCarico,
			OrdineScarico ordineScarico, Utente corriere, String nomeCorriere, 
			String cognomeCorriere, String tipoDocumentoCorriere,
			String numeroDocumentoCorriere, String telefonoCorriere, String emailCorriere, String targaCorriere) {
		super();
		this.data = data;
		this.fasciaOraria = fasciaOraria;
		this.OrdineCarico = OrdineCarico;
		this.ordineScarico = ordineScarico;
		this.corriere = corriere;
		this.nomeCorriere = nomeCorriere;
		this.cognomeCorriere = cognomeCorriere;
		this.tipoDocumentoCorriere = tipoDocumentoCorriere;
		this.numeroDocumentoCorriere = numeroDocumentoCorriere;
		this.telefonoCorriere = telefonoCorriere;
		this.emailCorriere = emailCorriere;
		this.targaCorriere = targaCorriere;
	}
	
	public Long getCodice() {
		return codice;
	}
	
	public void setCodice(Long codice) {
		this.codice = codice;
	}
	
	public String getData() {
		return data;
	}
	
	public void setData(String data) {
		this.data = data;
	}
	
	public String getFasciaOraria() {
		return fasciaOraria;
	}
	
	public void setFasciaOraria(String fasciaOraria) {
		this.fasciaOraria = fasciaOraria;
	}
	
	public List<Movimento> getMovimenti(){
		return movimenti;
	}
	
	public void setMovimenti(List<Movimento> movimenti) {
		this.movimenti = movimenti;
	}
	
	public Ordine getOrdineCarico() {
		return OrdineCarico;
	}
	
	public void setOrdineCarico(OrdineCarico OrdineCarico) {
		this.OrdineCarico = OrdineCarico;
	}

	public Ordine getOrdineScarico() {
		return ordineScarico;
	}
	
	public void setOrdineScarico(OrdineScarico ordineScarico) {
		this.ordineScarico = ordineScarico;
	}
	
	public Utente getCorriere() {
		return corriere;
	}
	
	public void setCorriere(Utente corriere) {
		this.corriere = corriere;
	}
	
	public String getNomeCorriere() {
		return nomeCorriere;
	}
	public void setNomeCorriere(String nome) {
		this.nomeCorriere = nome;
	}
	public String getCognomeCorriere() {
		return cognomeCorriere;
	}
	public void setCognomeCorriere(String cognome) {
		this.cognomeCorriere = cognome;
	}
	public String getTipoDocumentoCorriere() {
		return tipoDocumentoCorriere;
	}
	public void setTipoDocumentoCorriere(String tipoDocumento) {
		this.tipoDocumentoCorriere = tipoDocumento;
	}
	public String getNumeroDocumentoCorriere() {
		return numeroDocumentoCorriere;
	}
	public void setNumeroDocumentoCorriere(String numeroDocumento) {
		this.numeroDocumentoCorriere = numeroDocumento;
	}
	public String getTelefonoCorriere() {
		return telefonoCorriere;
	}
	public void setTelefonoCorriere(String telefono) {
		this.telefonoCorriere = telefono;
	}
	public String getEmailCorriere() {
		return emailCorriere;
	}
	public void setEmailCorriere(String email) {
		this.emailCorriere = email;
	}
	public String getTargaCorriere() {
		return targaCorriere;
	}
	public void setTargaCorriere(String targa) {
		this.targaCorriere = targa;
	}

}
