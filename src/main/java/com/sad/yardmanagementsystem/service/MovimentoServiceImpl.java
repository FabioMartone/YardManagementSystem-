package com.sad.yardmanagementsystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sad.yardmanagementsystem.model.Movimento;
import com.sad.yardmanagementsystem.model.TipoArea;
import com.sad.yardmanagementsystem.repository.RepositoryMovimento;

@Service
public class MovimentoServiceImpl implements MovimentoService{
	
	@Autowired 
	private RepositoryMovimento movimentoRepository;
	
	public Movimento createMovimento(Movimento movimento) {
		return movimentoRepository.save(movimento);
	}
	
	public Movimento updateMovimento(Movimento movimento) {
		return movimentoRepository.save(movimento);
	}
	
	public List<Movimento> getMovInSosta(TipoArea tipo){
		return movimentoRepository.getMovimentiInSosta(tipo);
	}

}
