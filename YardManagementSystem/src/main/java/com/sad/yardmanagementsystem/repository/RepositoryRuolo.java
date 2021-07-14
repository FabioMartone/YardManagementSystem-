package com.sad.yardmanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sad.yardmanagementsystem.model.Ruolo;

@Repository
public interface RepositoryRuolo extends JpaRepository<Ruolo, Long>{
	Ruolo findByNome(String nome);
}