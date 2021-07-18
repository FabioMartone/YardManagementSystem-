package com.sad.yardmanagementsystem.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sad.yardmanagementsystem.model.OrarioDisponibile;

@Repository
public interface RepositoryOrarioDisponibile extends JpaRepository<OrarioDisponibile, String>{
	Optional<OrarioDisponibile> findByFasciaOraria(String fasciaOraria);
}