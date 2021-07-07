package com.sad.yardmanagementsystem.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sad.yardmanagementsystem.model.Prenotazione;

@Repository
public interface RepositoryPrenotazione extends JpaRepository<Prenotazione, Long>{
	Optional<Prenotazione> findById(Long codice);
}