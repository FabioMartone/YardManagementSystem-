package com.sad.yardmanagementsystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sad.yardmanagementsystem.model.Movimento;
import com.sad.yardmanagementsystem.model.TipoArea;

@Repository
public interface RepositoryMovimento extends JpaRepository<Movimento, Long>{
	@Query("SELECT m FROM Movimento m WHERE (m.fineMovimento = '' and m.area.tipo = ?1) ORDER BY m.id")
	List<Movimento> getMovimentiInSosta(TipoArea tipo);
}
