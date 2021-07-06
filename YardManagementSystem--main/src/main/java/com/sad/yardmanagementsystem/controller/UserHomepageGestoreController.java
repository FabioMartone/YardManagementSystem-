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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sad.yardmanagementsystem.controller.dto.DepositoDto;
import com.sad.yardmanagementsystem.controller.dto.DepositoOrarioDto;
import com.sad.yardmanagementsystem.controller.dto.UtenteRegistrationDto;
import com.sad.yardmanagementsystem.model.Corriere;
import com.sad.yardmanagementsystem.model.DepositiCorrieri;
import com.sad.yardmanagementsystem.model.Deposito;
import com.sad.yardmanagementsystem.model.Utente;
import com.sad.yardmanagementsystem.repository.RepositoryDepositiCorrieri;
import com.sad.yardmanagementsystem.repository.RepositoryDeposito;
import com.sad.yardmanagementsystem.repository.RepositoryUtente;
import com.sad.yardmanagementsystem.service.EmailService;
import com.sad.yardmanagementsystem.service.UtenteService;


@Controller
@RequestMapping("/home_gestore")
public class UserHomepageGestoreController {

	private UtenteService userService;	
	@Autowired
	private RepositoryDeposito repositoryDeposito;
	@Autowired
	private RepositoryUtente utenteRepository;
	@Autowired 
	private RepositoryDepositiCorrieri repositoryDepositiCorrieri;
	@Autowired 
	private EmailService emailService;

	public UserHomepageGestoreController(UtenteService userService) {
		super();
		this.userService = userService;
	}
		
	@GetMapping
	public String showHomepage() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null && auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("GESTORE"))) {
			return "home_gestore";
		}
		else return "redirect:/home_corriere";
	}
	
	@GetMapping("/gestione-associazioni")
	public String showGestione(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null && auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("GESTORE"))) {
			
		    List<DepositiCorrieri> depositi_corrieri = repositoryDepositiCorrieri.findByDepositiCorrieriid_gestore(userService.loadUserByMail(auth.getName()).getId());
		    ArrayList<DepositiCorrieri> corrieri_associati = new ArrayList<>();
		    ArrayList<DepositiCorrieri> corrieri_in_attesa = new ArrayList<>();
		    for(DepositiCorrieri dc : depositi_corrieri) {
		    	if(dc.getStato().equals("ASSOCIATO")) { //ASSOCIATO, IN_ATTESA, RIFIUTATO
		    		corrieri_associati.add(dc);
		    	}
		    	else if(dc.getStato().equals("IN_ATTESA")) { //ASSOCIATO, IN_ATTESA, RIFIUTATO
		    		corrieri_in_attesa.add(dc);
		    	}
		    }
		    
		    
		    model.addAttribute("listCorrieriAssociati",corrieri_associati);
		    model.addAttribute("listCorrieriInAttesa",corrieri_in_attesa); 
		    
		    return "gestione-associazioni-gestori";
		}
		else return "redirect:/login"; 
	}
	
	@GetMapping("/gestione-associazioni/accettaAssociazioneMagazzino/{id}")
	public String accettaAssociazione(@PathVariable (value = "id") long id) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null && auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("GESTORE"))) {
			
			String stato = "ASSOCIATO";
			DepositiCorrieri dc = repositoryDepositiCorrieri.getById(id);
			
			
			dc.setStato("ASSOCIATO");
			
			repositoryDepositiCorrieri.save(dc);
			
			/*emailService.sendSimpleMessage(dc.getCorriere().getEmail(), "Richiesta di associazione Accettata", "Richiesta di associazione accettata da parte del "
					+ "gestore "+dc.getDeposito().getGestore().getRagioneSociale()+" per il deposito di "+dc.getDeposito().getIndirizzo());*/
			emailService.sendSimpleMessage("visonebenito@gmail.com", "Richiesta di associazione Accettata", "Richiesta di associazione accettata da parte del "
					+ "gestore "+dc.getDeposito().getGestore().getRagioneSociale()+" per il deposito di "+dc.getDeposito().getIndirizzo());
			
			return "redirect:/home_gestore/gestione-associazioni?accettato";
		}
		else return "redirect:/login"; 
	}
	
	@GetMapping("/gestione-associazioni/rifiutaAssociazioneMagazzino/{id}")
	public String rifiutaAssociazione(@PathVariable (value = "id") long id) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null && auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("GESTORE"))) {
			
			String stato = "RIFIUTATO";
			DepositiCorrieri dc = repositoryDepositiCorrieri.getById(id);
			
			
			dc.setStato("RIFIUTATO");
			
			repositoryDepositiCorrieri.save(dc);
			
			/*emailService.sendSimpleMessage(dc.getCorriere().getEmail(), "Richiesta di associazione Rifiutata", "Richiesta di associazione rifiutata da parte del "
					+ "gestore "+dc.getDeposito().getGestore().getRagioneSociale()+" per il deposito di "+dc.getDeposito().getIndirizzo());*/
			
			emailService.sendSimpleMessage("visonebenito@gmail.com", "Richiesta di associazione Rifiutata", "Richiesta di associazione rifiutata da parte del "
					+ "gestore "+dc.getDeposito().getGestore().getRagioneSociale()+" per il deposito di "+dc.getDeposito().getIndirizzo());
			
			
			return "redirect:/home_gestore/gestione-associazioni?rifiutato";
		}
		else return "redirect:/login";  
	}
	
	@GetMapping("/add_orario")
	public String showAddOrarioForm() {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null && auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("GESTORE"))) {
			
			return "add_orario";
		}
		else return "redirect:/login";
	}
	
	@ModelAttribute("DepositoOrario")
    public DepositoOrarioDto depositoOrarioDto() {
		
			return new DepositoOrarioDto();
    }
	
	@PostMapping("/add_orario")
	public String add_orario_gestore(@ModelAttribute("DepositoOrario") DepositoOrarioDto depositoOrarioDto) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null && auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("GESTORE"))) {
			
			if(!userService.deposito_exists(depositoOrarioDto)) {
				return "redirect:/home_gestore/add_orario?error0";
			}
			
			if(userService.orario_deposito_exists(depositoOrarioDto)) {
				return "redirect:/home_gestore/add_orario?error1";
			}
			
			if(!userService.fascia_oraria_exists(depositoOrarioDto)) {
				return "redirect:/home_gestore/add_orario?error2";
			}
			
			if(!userService.is_associated(auth.getName(), depositoOrarioDto)) {
				return "redirect:/home_gestore/add_orario?error3";
			}
			
			userService.add_orario_disp(depositoOrarioDto);
			return "redirect:/home_gestore/add_orario?success";
		}
		else return "redirect:/login";
	}
	
	@ModelAttribute("Deposito")
    public DepositoDto depositoDto() {
        
		return new DepositoDto();
        
    }
	
	@GetMapping("/add_deposito")
	public String showAddDepositoForm() {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null && auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("GESTORE"))) {
			
			return "add_deposito";
		}
		else return "redirect:/login";
	}

	@GetMapping("/add_deposito/add_area")
	public String showAddAreaForm() {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null && auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("GESTORE"))) {

			return "add_area";	
		}
		else return "redirect:/login";
	}
	
	String indirizzo;
	
	@PostMapping("/add_deposito/add_area")
	public String AddArea(@ModelAttribute("Deposito") DepositoDto depositoDto) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null && auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("GESTORE"))) {
		
			depositoDto.setIndirizzo(indirizzo);
			
			userService.add_area(depositoDto);
			
			return "redirect:/home_gestore/add_deposito/add_area?success";
			
		}else return "redirect:/login";
	}
	
	
	
	@PostMapping("/add_deposito")
	public String AddDeposito(@ModelAttribute("Deposito") DepositoDto depositoDto) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null && auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("GESTORE"))) {
		
			List<Deposito> depositi = repositoryDeposito.findAll();
			
			for(Deposito d : depositi) {
				
				if(d.getIndirizzo().equals(depositoDto.getIndirizzo())) {
					return "redirect:/home_gestore/add_deposito?error";
				}
			}
			
			Utente gestore = utenteRepository.findByEmail(auth.getName());
			
			indirizzo = depositoDto.getIndirizzo();
			
			userService.add_deposito(depositoDto, gestore);
					
			return "redirect:/home_gestore/add_deposito/add_area";
		}else return "redirect:/login"; 
	}
	
	@GetMapping("/visualizza_depositi")
	public String showVisualizzaDepositiForm(Model model) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null && auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("GESTORE"))) {
		
		    List<Deposito> depositi = repositoryDeposito.findAll();
		    
		    List<Deposito> depositi_gestore= new ArrayList<>();
		    
		    for(Deposito d : depositi) {
		    	
		    	if(d.getGestore().getId().equals(utenteRepository.findByEmail(auth.getName()).getId())) {
		    		depositi_gestore.add(d);
		    	}
		    }
		    
		    model.addAttribute("listDepositiGestore",depositi_gestore);
		    
		    return "visualizza_depositi";
		}
		else return "redirect:/login"; 
	}
}