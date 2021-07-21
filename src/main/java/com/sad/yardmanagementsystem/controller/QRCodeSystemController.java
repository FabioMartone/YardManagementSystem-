package com.sad.yardmanagementsystem.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sad.yardmanagementsystem.controller.dto.MyPojo;
import com.sad.yardmanagementsystem.model.Area;
import com.sad.yardmanagementsystem.model.Deposito;
import com.sad.yardmanagementsystem.model.Movimento;
import com.sad.yardmanagementsystem.model.OrdineCarico;
import com.sad.yardmanagementsystem.model.OrdineScarico;
import com.sad.yardmanagementsystem.model.Prenotazione;
import com.sad.yardmanagementsystem.model.TipoArea;
import com.sad.yardmanagementsystem.model.Utente;
import com.sad.yardmanagementsystem.service.AreaService;
import com.sad.yardmanagementsystem.service.EmailService;
import com.sad.yardmanagementsystem.service.MovimentoService;
import com.sad.yardmanagementsystem.service.OrdineService;

@Controller
@RequestMapping("/api/home_external_system")
public class QRCodeSystemController {
		
		@Autowired
		private OrdineService ordineService;
		
		@Autowired
		private MovimentoService movimentoService;
		
		@Autowired
		private AreaService areaService;
		
		@Autowired 
		private EmailService mailService;
		
		@Resource(name="authenticationManager")
		private AuthenticationManager authManager;
		

		@PostMapping(value = "/update_book_information",
				consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
		        produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})		
		public ResponseEntity<String> updateBookInformation(
				@RequestBody MyPojo mypojo) {
							
			UsernamePasswordAuthenticationToken authReq
		      = new UsernamePasswordAuthenticationToken(mypojo.getUser(), mypojo.getPassword());
		    Authentication auth = authManager.authenticate(authReq);
		    
		    SecurityContext sc = SecurityContextHolder.getContext();
		    sc.setAuthentication(auth);
		    
		    if (auth != null && auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("EXTERNAL_SYSTEM_SCANNER"))) {
		    	
		    		OrdineCarico ordCar = ordineService.getOrdineCaricoByChiavePren(mypojo.getKey());
	    			OrdineScarico ordScar = null;
	    			Prenotazione pren = null;
	    			Utente gestore = null;
	    			String numeroOrdine = null;
	    			Deposito deposito;
	    			if(ordCar == null) {
	    				ordScar = ordineService.getOrdineScaricoByChiavePren(mypojo.getKey());
	    				deposito = ordScar.getDeposito();
	    				pren = ordScar.getPrenotazione();
	    				gestore = ordScar.getDeposito().getGestore();
	    				numeroOrdine = ordScar.getNumero();
	    			}
	    			else {
	    				deposito = ordCar.getDeposito();
	    				pren = ordCar.getPrenotazione();
	    				gestore = ordCar.getDeposito().getGestore();
	    				numeroOrdine = ordCar.getNumero();
	    			}
		    	
	    		
	    	if(checkData(pren.getData())){
	    			
		    	if(mypojo.getType().toLowerCase().equals("in")){
		    		
		    		if(checkPrenMov(pren)){
		    		List<Area> listAree = deposito.getAree();
		    		System.out.println("Deposito: "+deposito.getId());
		    		for(Area a : listAree) {
		    			System.out.println("Area: "+a.getDescrizione()+" "+a.getCodice());
		    		}
		    		Area foundArea = null;
		    		boolean found_work_0 = false;
		    		boolean found_rest_0 = false;
		    		
		    		for(Area a : listAree) {
		    			if(a.getTipo().equals(TipoArea.LAVORO) && a.getFlag() == 0) {
		    				found_work_0 = true;
		    				foundArea = a;
		    		    	mailService.sendSimpleMessage(pren.getEmailCorriere(), "Assegnata un'area di lavoro", "Gentile Corriere, le è stata assegnata l'area di lavoro "+a.getDescrizione()+".\nCordiali saluti.");

		    			}
		    		}
		    		if(!found_work_0) {
		    			for(Area a : listAree) {
			    			if(a.getTipo().equals(TipoArea.SOSTA) && a.getFlag() == 0) {
			    				found_rest_0 = true;
			    				foundArea = a;
			    		    mailService.sendSimpleMessage(pren.getEmailCorriere(), "Assegnata un'area di sosta", "Gentile Corriere, le è stata assegnata l'area di sosta "+a.getDescrizione()+".\nCordiali saluti.");

			    			}
			    		}
		    		}
		    		
		    		if(!found_work_0 && !found_rest_0) {
		    			
		    			mailService.sendSimpleMessage(pren.getEmailCorriere(), "Aree Disponibili Terminate", "Attenzione, aree disponibli terminate, sarà contattato dal gestore a breve.\nCordiali saluti.");
				    	mailService.sendSimpleMessage(gestore.getEmail(), "Aree Disponibili Terminare", "Attenzione, è presente al carico un corriere ma sono terminate le aree disponibili. Contattare al più presto il referente.\nNumero Ordine: "+numeroOrdine+"\nTelefono fornito in fase di prenotazione: "+pren.getTelefonoCorriere()+"\nEmail fornita in fase di prenotazione: "+pren.getEmailCorriere()+"\nCordiali saluti.");
				    	
				           return new ResponseEntity<String>(HttpStatus.NO_CONTENT); 
		    		}
		    		
		    		Movimento mov = new Movimento(mypojo.getTimestamp_reg(),"",foundArea,pren);
		    		
		    		movimentoService.createMovimento(mov);
		    		foundArea.setflag(1);
		    		areaService.updateArea(foundArea);
		    	}else {
		    		mailService.sendSimpleMessage(pren.getEmailCorriere(), "Data arrivo non conforme con data prevista o ingresso già effettuato", "Attenzione, la data prevista di arrivo non corrisponde alla data odierna.\nA breve sarà contattato dal Gestore.\nCordiali saluti.");
			    	mailService.sendSimpleMessage(gestore.getEmail(), "Data arrivo non conforme con data prevista o ingresso già effettuato", "Attenzione, è presente al carico un corriere non previsto. Contattare al più presto il referente.\nNumero Ordine: "+numeroOrdine+"\nTelefono fornito in fase di prenotazione: "+pren.getTelefonoCorriere()+"\nEmail fornita in fase di prenotazione: "+pren.getEmailCorriere()+"\nCordiali saluti.");
			    	return new ResponseEntity<String>(HttpStatus.NO_CONTENT); 
		    	}
		    	}	
		    	else if(mypojo.getType().toLowerCase().equals("out")) {
		    				    		
		    		List<Movimento> listMovimenti = pren.getMovimenti();
		    		Area area = null;
		    		boolean found_mov_in = false;
		    		for(Movimento m : listMovimenti) {
		    			if(m.getArea().getTipo().equals(TipoArea.LAVORO) && m.getFineMovimento().equals("")) {
		    				area = m.getArea();
		    				area.setflag(0);
		    				m.setFineMovimento(mypojo.getTimestamp_reg());
		    				areaService.updateArea(area);
		    				movimentoService.updateMovimento(m);
		    				found_mov_in = true;
		    				
		    			}
		    		}
		    		if(found_mov_in) {
		    		List<Movimento> listMovimento = movimentoService.getMovInSosta(TipoArea.SOSTA);
		    		if(listMovimento.size()>0) {
		    			Movimento movScelto = listMovimento.get(0);
		    			Area area_old = movScelto.getArea();
		    			area_old.setflag(0);
		    			movScelto.setFineMovimento(mypojo.getTimestamp_reg());
		    			areaService.updateArea(area_old);
		    			movimentoService.updateMovimento(movScelto);
		    			
			    		Movimento mov = new Movimento(mypojo.getTimestamp_reg(),"",area,movScelto.getPrenotazione());
			    		area.setflag(1);
			    		movimentoService.createMovimento(mov);
			    		areaService.updateArea(area);
	    		    	mailService.sendSimpleMessage(movScelto.getPrenotazione().getEmailCorriere(), "Assegnata un'area di lavoro", "Gentile Corriere, le è stata assegnata l'area di lavoro "+area.getDescrizione()+".\nCordiali saluti.");

		    		}
		    	}else {
			           return new ResponseEntity<String>(HttpStatus.NO_CONTENT); 
		    	}
		    	}
		          return new ResponseEntity<String>(HttpStatus.OK); 

		    }else {
		    	
		    	mailService.sendSimpleMessage(pren.getEmailCorriere(), "Data arrivo non conforme con data prevista o ingresso già effettuato", "Attenzione, la data prevista di arrivo non corrisponde alla data odierna.\nA breve sarà contattato dal Gestore.\nCordiali saluti.");
		    	mailService.sendSimpleMessage(gestore.getEmail(), "Data arrivo non conforme con data prevista o ingresso già effettuato", "Attenzione, è presente al carico un corriere non previsto. Contattare al più presto il referente.\nNumero Ordine: "+numeroOrdine+"\nTelefono fornito in fase di prenotazione: "+pren.getTelefonoCorriere()+"\nEmail fornita in fase di prenotazione: "+pren.getEmailCorriere()+"\nCordiali saluti.");
		    	return new ResponseEntity<String>(HttpStatus.NO_CONTENT); 
		    }

		    }
		    else {
		           return new ResponseEntity<String>(HttpStatus.FORBIDDEN); 

		    }
		    
			
		}
		
	public boolean checkData(String data) {
		   DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");  
		   LocalDateTime now = LocalDateTime.now();  

		   if(dtf.format(now).equals(data)) {
			   return true;
		   }
		   else return false;
		
		
	}
	
	public boolean checkPrenMov(Prenotazione prenotazione) {
		List<Movimento> listMovimenti = prenotazione.getMovimenti();
		if(listMovimenti.size() == 0) {
			return true;
		}
		else return false;
	}
		

}

