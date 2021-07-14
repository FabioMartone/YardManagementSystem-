package com.sad.yardmanagementsystem.service;

import com.sad.yardmanagementsystem.controller.dto.OrdineCaricoDto;
import com.sad.yardmanagementsystem.controller.dto.OrdineScaricoDto;

public interface OrdineService {
	
	void add_ordine_carico(OrdineCaricoDto ordineCaricoDto,String chiave);
	
	void add_ordine_scarico(OrdineScaricoDto ordineScaricoDto,String chiave);

}
