package com.sad.yardmanagementsystem.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sad.yardmanagementsystem.model.OrdineCarico;
import com.sad.yardmanagementsystem.model.OrdineScarico;

import net.bytebuddy.dynamic.DynamicType.Builder.FieldDefinition.Optional;

@Repository
public interface RepositoryOrdineScarico extends JpaRepository<OrdineScarico, Long>{
	OrdineScarico findByChiaveOrdine(Long chiave);
	
}
