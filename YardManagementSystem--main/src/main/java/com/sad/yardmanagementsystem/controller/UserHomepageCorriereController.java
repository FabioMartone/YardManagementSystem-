package com.sad.yardmanagementsystem.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sad.yardmanagementsystem.model.DepositiCorrieri;
import com.sad.yardmanagementsystem.model.Deposito;
import com.sad.yardmanagementsystem.model.Utente;
import com.sad.yardmanagementsystem.repository.RepositoryDepositiCorrieri;
import com.sad.yardmanagementsystem.repository.RepositoryDeposito;
import com.sad.yardmanagementsystem.service.EmailService;
import com.sad.yardmanagementsystem.service.UtenteService;


@Controller
@RequestMapping("/home_corriere")
public class UserHomepageCorriereController {

	private UtenteService userService;
	
	@Autowired
	private RepositoryDeposito repositoryDeposito;
	@Autowired 
	private RepositoryDepositiCorrieri repositoryDepositiCorrieri;
	@Autowired 
	private EmailService emailService;
	
	public UserHomepageCorriereController(UtenteService userService) {
		super();
		this.userService = userService;
	}
		
	@GetMapping
	public String showHomepage() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null && auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("CORRIERE"))) {
			return "home_corriere";
		}
		else return "redirect:/home_gestore";
	}
	
	@GetMapping("/gestione-associazioni")
	public String showGestione(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null && auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("CORRIERE"))) {
		
		    List<DepositiCorrieri> depositi_corrieri = repositoryDepositiCorrieri.findByDepositiCorrieriid_corriere(userService.loadUserByMail(auth.getName()).getId());
		    ArrayList<Deposito> depositi_associati = new ArrayList<>();
		    ArrayList<Deposito> depositi_in_attesa = new ArrayList<>();
		    ArrayList<Long> depositi_id = new ArrayList<>();
		    for(DepositiCorrieri dc : depositi_corrieri) {
		    	if(dc.getStato().equals("ASSOCIATO")) { //ASSOCIATO, IN_ATTESA, RIFIUTATO
		    		depositi_associati.add(dc.getDeposito());
		    		depositi_id.add(dc.getDeposito().getId());
		    	}
		    	else if(dc.getStato().equals("IN_ATTESA")) { //ASSOCIATO, IN_ATTESA, RIFIUTATO
		    		depositi_in_attesa.add(dc.getDeposito());
		    		depositi_id.add(dc.getDeposito().getId());
		    	}
		    }
		    
		    
		    model.addAttribute("listDepositiAssociati",depositi_associati);
		    model.addAttribute("listDepositiInAttesa",depositi_in_attesa);
		    
			List<Deposito> depositiAssociabili = repositoryDeposito.findAll();
			ArrayList<Deposito> depositiAssociabiliView = new ArrayList<>();
			int cont = 0;
			
			for(Deposito d : depositiAssociabili) {
				if(!depositi_id.contains(d.getId())) {
					depositiAssociabiliView.add(d);
				}
			}
		    
			model.addAttribute("listDepositiAssociabili",depositiAssociabiliView);
		    
		    return "gestione-associazioni";
		}
		else return "redirect:/login"; 
	}
	
	@GetMapping("/gestione-associazioni/richiediAssociazioneMagazzino/{id}")
	public String richiediAssociazione(@PathVariable (value = "id") long id) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null && auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("CORRIERE"))) {
			
			String stato = "IN_ATTESA";
			Optional<Deposito> depositoOpt = repositoryDeposito.findById(id);
			Deposito deposito = depositoOpt.get();
			
			Utente utente = userService.loadUserByMail(auth.getName());
			
			repositoryDepositiCorrieri.save(new DepositiCorrieri(utente, deposito, stato));
			
			emailService.sendSimpleMessage(deposito.getGestore().getEmail(), "Richiesta di associazione", "Richiesta di associazione da parte del "
					+ "corriere "+utente.getRagioneSociale()+".\nAccedi per Confermare o Rifiutare: localhost:8080");
			
			
			return "redirect:/home_corriere/gestione-associazioni?associazione_richiesta";
		}
		else return "redirect:/login"; 
	}
	
}