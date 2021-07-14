package com.sad.yardmanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sad.yardmanagementsystem.model.OrdineCarico;

import java.util.List;
import java.util.Optional;

@Repository
public interface RepositoryOrdineCarico extends JpaRepository<OrdineCarico, Long> {
	Optional<OrdineCarico> findByChiave(Long chiave);
	OrdineCarico findByChiavePrenotazione(String chiave);
	
	@Query("SELECT oc FROM OrdineCarico oc WHERE oc.deposito.gestore.email=?1")
	List<OrdineCarico> findByEmailGestore(String emailGestore);
}
