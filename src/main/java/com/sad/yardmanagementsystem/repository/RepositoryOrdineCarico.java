package com.sad.yardmanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sad.yardmanagementsystem.model.OrdineCarico;

import net.bytebuddy.dynamic.DynamicType.Builder.FieldDefinition.Optional;

@Repository
public interface RepositoryOrdineCarico extends JpaRepository<OrdineCarico, Long> {
	Optional<OrdineCarico> findByChiave(Long chiave);
	OrdineCarico findByChiaveOrdine(Long chiave);
	
}
