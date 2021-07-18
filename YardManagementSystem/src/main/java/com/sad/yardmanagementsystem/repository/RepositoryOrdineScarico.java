package com.sad.yardmanagementsystem.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sad.yardmanagementsystem.model.OrdineScarico;

import java.util.List;
import java.util.Optional;

@Repository
public interface RepositoryOrdineScarico extends JpaRepository<OrdineScarico, Long>{
	Optional<OrdineScarico> findByChiave(Long chiave);
	OrdineScarico findByChiavePrenotazione(String chiave);
	
	@Query("SELECT os FROM OrdineScarico os WHERE os.deposito.gestore.email=?1")
	List<OrdineScarico> findByEmailGestore(String emailGestore);
}
