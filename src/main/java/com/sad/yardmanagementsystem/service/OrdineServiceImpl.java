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
		
		char[] temp = new char[ordineCaricoDto.getDataPrevista().toCharArray().length];
		for(int i=0; i<2; i++) {
			temp[i]=ordineCaricoDto.getDataPrevista().toCharArray()[ordineCaricoDto.getDataPrevista().toCharArray().length-2+i];
		}
		for(int i=0; i<2; i++) {
			temp[i+3]=ordineCaricoDto.getDataPrevista().toCharArray()[ordineCaricoDto.getDataPrevista().toCharArray().length-5+i];
		}
		for(int i=0; i<4; i++) {
			temp[i+6]=ordineCaricoDto.getDataPrevista().toCharArray()[ordineCaricoDto.getDataPrevista().toCharArray().length-10+i];
		}
		temp[2]='-';
		temp[5]='-';
		String dataPrevista= new String(temp);
		
		
		temp = new char[ordineCaricoDto.getDataMercePronta().toCharArray().length];
		for(int i=0; i<2; i++) {
			temp[i]=ordineCaricoDto.getDataMercePronta().toCharArray()[ordineCaricoDto.getDataMercePronta().toCharArray().length-2+i];
		}
		for(int i=0; i<2; i++) {
			temp[i+3]=ordineCaricoDto.getDataMercePronta().toCharArray()[ordineCaricoDto.getDataMercePronta().toCharArray().length-5+i];
		}
		for(int i=0; i<4; i++) {
			temp[i+6]=ordineCaricoDto.getDataMercePronta().toCharArray()[ordineCaricoDto.getDataMercePronta().toCharArray().length-10+i];
		}
		temp[2]='-';
		temp[5]='-';
		String dataMercePronta=new String(temp);
		
		OrdineCarico ordineCarico = new OrdineCarico(ordineCaricoDto.getNumero() , dataPrevista, ordineCaricoDto.getNumeroColli(), ordineCaricoDto.getNumeroColonne(), ordineCaricoDto.getNumeroPedane(), ordineCaricoDto.getPesoTotale(),chiave, repositoryDeposito.findById(ordineCaricoDto.getDeposito()).get() , dataMercePronta, ordineCaricoDto.getClienteDestinatario(), ordineCaricoDto.getIndirizzoDestinatario(), null );
		ordineCaricoRepository.save(ordineCarico);
	}
	
	public void add_ordine_scarico(OrdineScaricoDto ordineScaricoDto, String chiave) {
			
			Fornitore f = new Fornitore(ordineScaricoDto.getMailFornitore(),ordineScaricoDto.getRagioneSocialeFornitore(), ordineScaricoDto.getIndirizzoFornitore(), ordineScaricoDto.getTelefonoFornitore());
		
			char[] temp = new char[ordineScaricoDto.getDataPrevista().toCharArray().length];
			for(int i=0; i<2; i++) {
				temp[i]=ordineScaricoDto.getDataPrevista().toCharArray()[ordineScaricoDto.getDataPrevista().toCharArray().length-2+i];
			}
			//2021-07-06
			for(int i=0; i<2; i++) {
				temp[i+3]=ordineScaricoDto.getDataPrevista().toCharArray()[ordineScaricoDto.getDataPrevista().toCharArray().length-5+i];
			}
			for(int i=0; i<4; i++) {
				temp[i+6]=ordineScaricoDto.getDataPrevista().toCharArray()[ordineScaricoDto.getDataPrevista().toCharArray().length-10+i];
			}
			temp[2]='-';
			temp[5]='-';
			String dataPrevista= new String(temp);
			
			OrdineScarico ordineScarico = new OrdineScarico(ordineScaricoDto.getNumero() , dataPrevista, ordineScaricoDto.getNumeroColli(), ordineScaricoDto.getNumeroColonne(), ordineScaricoDto.getNumeroPedane(), ordineScaricoDto.getPesoTotale(),chiave, repositoryDeposito.findById(ordineScaricoDto.getDeposito()).get() ,f , null );
			ordineScaricoRepository.save(ordineScarico);
		}
	
	public OrdineScarico getOrdineScaricoByChiavePren(String chiave) {
		
		return ordineScaricoRepository.findByChiavePrenotazione(chiave);
	}
	
	public OrdineCarico getOrdineCaricoByChiavePren(String chiave) {
		
		return ordineCaricoRepository.findByChiavePrenotazione(chiave);

	}
}
