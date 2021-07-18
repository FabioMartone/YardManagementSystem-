package com.sad.yardmanagementsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sad.yardmanagementsystem.controller.dto.OrdineCaricoDto;
import com.sad.yardmanagementsystem.controller.dto.OrdineScaricoDto;
import com.sad.yardmanagementsystem.model.Fornitore;
import com.sad.yardmanagementsystem.model.OrdineCarico;
import com.sad.yardmanagementsystem.model.OrdineScarico;
import com.sad.yardmanagementsystem.repository.RepositoryDeposito;
import com.sad.yardmanagementsystem.repository.RepositoryOrdineCarico;
import com.sad.yardmanagementsystem.repository.RepositoryOrdineScarico;


@Service
public class OrdineServiceImpl implements OrdineService {
	
    @Autowired
	private RepositoryOrdineCarico ordineCaricoRepository;
    
    @Autowired
	private RepositoryOrdineScarico ordineScaricoRepository;
    
    @Autowired
   	private RepositoryDeposito repositoryDeposito;
    
	
	public void add_ordine_carico(OrdineCaricoDto ordineCaricoDto,String chiave) {
				
		OrdineCarico ordineCarico = new OrdineCarico(ordineCaricoDto.getNumero() ,ordineCaricoDto.getDataPrevista(), ordineCaricoDto.getNumeroColli(), ordineCaricoDto.getNumeroColonne(), ordineCaricoDto.getNumeroPedane(), ordineCaricoDto.getPesoTotale(),chiave, repositoryDeposito.findById(ordineCaricoDto.getDeposito()).get() ,ordineCaricoDto.getDataMercePronta(), ordineCaricoDto.getClienteDestinatario(), ordineCaricoDto.getIndirizzoDestinatario(), null );
		ordineCaricoRepository.save(ordineCarico);
	}
	
	public void add_ordine_scarico(OrdineScaricoDto ordineScaricoDto, String chiave) {
			
			Fornitore f = new Fornitore(ordineScaricoDto.getMailFornitore(),ordineScaricoDto.getRagioneSocialeFornitore(), ordineScaricoDto.getIndirizzoFornitore(), ordineScaricoDto.getTelefonoFornitore());
		
			OrdineScarico ordineScarico = new OrdineScarico(ordineScaricoDto.getNumero() ,ordineScaricoDto.getDataPrevista(), ordineScaricoDto.getNumeroColli(), ordineScaricoDto.getNumeroColonne(), ordineScaricoDto.getNumeroPedane(), ordineScaricoDto.getPesoTotale(),chiave, repositoryDeposito.findById(ordineScaricoDto.getDeposito()).get() ,f , null );
			ordineScaricoRepository.save(ordineScarico);
		}
	
	public OrdineScarico getOrdineScaricoByChiavePren(String chiave) {
		
		return ordineScaricoRepository.findByChiavePrenotazione(chiave);
	}
	
	public OrdineCarico getOrdineCaricoByChiavePren(String chiave) {
		
		return ordineCaricoRepository.findByChiavePrenotazione(chiave);

	}
}
