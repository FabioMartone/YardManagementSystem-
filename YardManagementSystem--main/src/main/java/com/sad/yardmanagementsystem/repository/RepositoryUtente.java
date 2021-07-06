package com.sad.yardmanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sad.yardmanagementsystem.model.Deposito;
import com.sad.yardmanagementsystem.model.Utente;

@Repository
public interface RepositoryUtente extends JpaRepository<Utente, Long>{
	Utente findByEmail(String email);
}