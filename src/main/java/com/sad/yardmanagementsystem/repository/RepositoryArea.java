package com.sad.yardmanagementsystem.repository;

import java.util.List; 

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sad.yardmanagementsystem.model.Area;
import com.sad.yardmanagementsystem.model.TipoArea;

@Repository
public interface RepositoryArea extends JpaRepository<Area, Long>{
	@Query("SELECT a FROM Area a WHERE a.deposito.id=?1 AND a.tipo=?2")
	List<Area> findByDepositoTipo(Long id_deposito, TipoArea Tipo);
}