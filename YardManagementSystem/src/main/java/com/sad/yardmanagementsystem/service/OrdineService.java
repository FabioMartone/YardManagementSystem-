package com.sad.yardmanagementsystem.service;


import com.sad.yardmanagementsystem.controller.dto.OrdineCaricoDto;
import com.sad.yardmanagementsystem.controller.dto.OrdineScaricoDto;
import com.sad.yardmanagementsystem.model.OrdineCarico;
import com.sad.yardmanagementsystem.model.OrdineScarico;

public interface OrdineService {
	
	void add_ordine_carico(OrdineCaricoDto ordineCaricoDto,String chiave);
	
	void add_ordine_scarico(OrdineScaricoDto ordineScaricoDto,String chiave);
	
	OrdineScarico getOrdineScaricoByChiavePren(String chiave);
	
	OrdineCarico getOrdineCaricoByChiavePren(String chiave);

}
