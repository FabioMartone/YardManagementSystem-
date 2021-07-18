package com.sad.yardmanagementsystem.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sad.yardmanagementsystem.model.Prenotazione;

@Repository
public interface RepositoryPrenotazione extends JpaRepository<Prenotazione, Long>{
	Optional<Prenotazione> findById(Long codice);
	
	@Query("SELECT p FROM Prenotazione p WHERE p.OrdineCarico.chiave=?1 ")
	Prenotazione findByOrdineCarico(Long codice);
	
	@Query("SELECT p FROM Prenotazione p WHERE p.ordineScarico.chiave=?1 ")
	Prenotazione findByOrdineScarico(Long codice);
	
	@Query("SELECT p FROM Prenotazione p WHERE p.OrdineCarico.deposito.id = ?1 AND p.fasciaOraria = ?2 AND p.OrdineCarico.dataMercePronta=?3")
	List<Prenotazione> findByIdDepositoCaricoFasciaOraria(Long id_deposito, String fasciaOraria, String data);
	
	@Query("SELECT p FROM Prenotazione p WHERE p.ordineScarico.deposito.id = ?1 AND p.fasciaOraria = ?2 AND p.ordineScarico.dataPrevista=?3")
	List<Prenotazione> findByIdDepositoScaricoFasciaOraria(Long id_deposito, String fasciaOraria, String data);
	
	@Query("SELECT p FROM Prenotazione p WHERE p.corriere.id=?1")
	List<Prenotazione> findByIdCorriere(Long id_corriere);
}