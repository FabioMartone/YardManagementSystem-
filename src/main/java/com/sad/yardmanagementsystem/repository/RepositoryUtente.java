package com.sad.yardmanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sad.yardmanagementsystem.model.Utente;

@Repository
public interface RepositoryUtente extends JpaRepository<Utente, Long>{
	Utente findByEmail(String email);
	
	@Query("SELECT p FROM Utente p WHERE p.id=?1 ")
	Utente findByid_utente(Long id);
}