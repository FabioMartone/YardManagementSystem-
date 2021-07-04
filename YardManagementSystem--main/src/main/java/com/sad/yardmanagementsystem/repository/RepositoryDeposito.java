package com.sad.yardmanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sad.yardmanagementsystem.model.Deposito;

@Repository
public interface RepositoryDeposito extends JpaRepository<Deposito, Long>{
	
}
