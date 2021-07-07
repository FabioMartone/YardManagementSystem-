package com.sad.yardmanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sad.yardmanagementsystem.model.OrdineCarico;

@Repository
public interface RepositoryOrdineCarico extends JpaRepository<OrdineCarico, Long> {

	OrdineCarico findByChiaveOrdine(Long chiave);
	
}
