package com.sad.yardmanagementsystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sad.yardmanagementsystem.model.DepositiCorrieri;

@Repository
public interface RepositoryDepositiCorrieri extends JpaRepository<DepositiCorrieri, Long>{
	@Query("SELECT d FROM depositi_corrieri d WHERE d.corriere.id = ?1")
	List<DepositiCorrieri> findByDepositiCorrieriid_corriere(Long id);
	
	@Query("SELECT d FROM depositi_corrieri d WHERE d.deposito.gestore.id = ?1")
	List<DepositiCorrieri> findByDepositiCorrieriid_gestore(Long id);
		
}