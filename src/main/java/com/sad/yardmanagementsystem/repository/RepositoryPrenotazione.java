package com.sad.yardmanagementsystem.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sad.yardmanagementsystem.model.OrdineScarico;
import com.sad.yardmanagementsystem.model.Prenotazione;

@Repository
public interface RepositoryPrenotazione extends JpaRepository<Prenotazione, Long>{
	Optional<Prenotazione> findById(Long codice);
	
	@Query("SELECT p FROM Prenotazione p WHERE p.OrdineCarico.chiave=?1 ")
	Prenotazione findByOrdineCarico(Long codice);
	
	@Query("SELECT p FROM Prenotazione p WHERE p.ordineScarico.chiave=?1 ")
	Prenotazione findByOrdineScarico(Long codice);
}