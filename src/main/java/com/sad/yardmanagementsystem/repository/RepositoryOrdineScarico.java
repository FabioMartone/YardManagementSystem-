package com.sad.yardmanagementsystem.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sad.yardmanagementsystem.model.OrdineScarico;

@Repository
public interface RepositoryOrdineScarico extends JpaRepository<OrdineScarico, Long>{

	OrdineScarico findByChiaveOrdine(Long chiave);
	
}
