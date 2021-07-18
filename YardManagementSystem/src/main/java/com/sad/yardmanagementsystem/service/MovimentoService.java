package com.sad.yardmanagementsystem.service;

import java.util.List;

import com.sad.yardmanagementsystem.model.Movimento;
import com.sad.yardmanagementsystem.model.TipoArea;

public interface MovimentoService {
	
	Movimento createMovimento(Movimento movimento);
	Movimento updateMovimento(Movimento movimento);
	List<Movimento> getMovInSosta(TipoArea tipo);

}
