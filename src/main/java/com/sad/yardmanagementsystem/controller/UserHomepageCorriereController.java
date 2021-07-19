package com.sad.yardmanagementsystem.controller;

import java.awt.image.BufferedImage;
import java.util.ArrayList;    
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sad.yardmanagementsystem.model.Area;
import com.sad.yardmanagementsystem.model.DepositiCorrieri;
import com.sad.yardmanagementsystem.model.Deposito;
import com.sad.yardmanagementsystem.model.OrarioDisponibile;
import com.sad.yardmanagementsystem.model.Ordine;
import com.sad.yardmanagementsystem.model.OrdineCarico;
import com.sad.yardmanagementsystem.model.OrdineScarico;
import com.sad.yardmanagementsystem.model.Prenotazione;
import com.sad.yardmanagementsystem.model.TipoArea;
import com.sad.yardmanagementsystem.model.Utente;
import com.sad.yardmanagementsystem.repository.RepositoryArea;
import com.sad.yardmanagementsystem.repository.RepositoryDepositiCorrieri;
import com.sad.yardmanagementsystem.repository.RepositoryDeposito;
import com.sad.yardmanagementsystem.repository.RepositoryOrdineCarico;
import com.sad.yardmanagementsystem.repository.RepositoryOrdineScarico;
import com.sad.yardmanagementsystem.repository.RepositoryPrenotazione;
import com.sad.yardmanagementsystem.repository.RepositoryUtente;
import com.sad.yardmanagementsystem.service.EmailService;
import com.sad.yardmanagementsystem.service.PrenotazioneService;
import com.sad.yardmanagementsystem.service.QRCodeService;
import com.sad.yardmanagementsystem.service.UtenteService;

import com.sad.yardmanagementsystem.controller.dto.CorrierePrenotazioneDto;
import com.sad.yardmanagementsystem.controller.dto.ViewOrdine;
import com.sad.yardmanagementsystem.controller.dto.ViewPrenotazioni;


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
	@Autowired
	private PrenotazioneService prenotazioneService;
	@Autowired
	private RepositoryUtente repUtente;
	@Autowired
	private RepositoryOrdineCarico repOrdineCarico;
	@Autowired
	private RepositoryOrdineScarico repOrdineScarico;
	@Autowired
	private RepositoryPrenotazione prenotazioneRepository;
	@Autowired
	private RepositoryArea areaRepository;
	
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
	
	
	@GetMapping("/effettua_prenotazione")
	public String showEffettuaPrenotazione() {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null && auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("CORRIERE"))) {
			
			return "effettua_prenotazione";
		}
		else return "redirect:/login";
	}
	
	@ModelAttribute("Prenotazione")
    public CorrierePrenotazioneDto prenotazioneDto() {
		
			return new CorrierePrenotazioneDto();
    }
	
	@PostMapping("/effettua_prenotazione")
	public String effettuaPrenotazione(@ModelAttribute("Prenotazione") CorrierePrenotazioneDto prenotazioneDto, Model model) {
		
		Long deposito_id;
		Long chiave_carico=0l;
		Long chiave_scarico=0l;
		String chiavePrenotazione;
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null && auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("CORRIERE"))) {
			
	
			if (!prenotazioneService.ordine_exists(prenotazioneDto)) {
				return "redirect:/home_corriere/effettua_prenotazione?error";
			}
			
			if (!prenotazioneService.associationExists(prenotazioneDto,repUtente.findByEmail(auth.getName()).getId())) {
				return "redirect:/home_corriere/gestione-associazioni?error";
			}
			if(prenotazioneService.prenotazione_already_exists(prenotazioneDto)) {
				return "redirect:/home_corriere/effettua_prenotazione?error0";
			}
			
			OrdineCarico ordineCarico = repOrdineCarico.findByChiavePrenotazione(prenotazioneDto.getChiavePrenotazione());
			OrdineScarico ordineScarico = repOrdineScarico.findByChiavePrenotazione(prenotazioneDto.getChiavePrenotazione());
						
			if (ordineCarico==null) {
				chiave_scarico=ordineScarico.getChiave();
				deposito_id = ordineScarico.getDeposito().getId();
			}
			else {
				chiave_carico=ordineCarico.getChiave();
				deposito_id = ordineCarico.getDeposito().getId();
			}
			
			chiavePrenotazione=prenotazioneDto.getChiavePrenotazione();
			
			model.addAttribute("chiavePrenotazione", chiavePrenotazione);
			
						
			
			Optional<Deposito> dep = repositoryDeposito.findById(deposito_id);
			
			TipoArea t = TipoArea.LAVORO;
			
			List<Area> aree = areaRepository.findByDepositoTipo(deposito_id, t);
			
			Deposito deposito = dep.get();
			
			List<OrarioDisponibile> orari = deposito.getOrariDisponibili();
			
			List<OrarioDisponibile> to_remove = new ArrayList<>();
			
			List<Prenotazione> prenotazioniCarico;
			List<Prenotazione> prenotazioniScarico;
			
			String data;
			
			ViewPrenotazioni v;
			
			if(chiave_carico==0l) {
				OrdineScarico o= repOrdineScarico.findByChiavePrenotazione(prenotazioneDto.getChiavePrenotazione());
				
				data = o.getDataPrevista();
				v = new ViewPrenotazioni(data, o.getDeposito().getIndirizzo(), 
						o.getNumero(), o.getNumeroColli(), o.getNumeroColonne(), 
						o.getNumeroPedane(), o.getPesoTotale());
				
			}
			else {
				OrdineCarico o= repOrdineCarico.findByChiavePrenotazione(prenotazioneDto.getChiavePrenotazione());
				
				data = o.getDataMercePronta();
				v = new ViewPrenotazioni(data, o.getDeposito().getIndirizzo(), 
						o.getNumero(), o.getNumeroColli(), o.getNumeroColonne(), 
						o.getNumeroPedane(), o.getPesoTotale());
			}
			
			model.addAttribute("ordine", v);
			
			for(OrarioDisponibile orario : orari) {
				
				prenotazioniCarico=prenotazioneRepository.findByIdDepositoCaricoFasciaOraria(deposito_id, orario.getFasciaOraria(), data);
				prenotazioniScarico=prenotazioneRepository.findByIdDepositoScaricoFasciaOraria(deposito_id, orario.getFasciaOraria(), data);
	 
				
				if((prenotazioniCarico.size()+prenotazioniScarico.size())>=aree.size()) {
					to_remove.add(orario);
				}
			}
			
			
			
			orari.removeAll(to_remove);
			
			
			model.addAttribute("listOrari", orari);		
			
		    
			List<String> tipi = new ArrayList<>();
			
			tipi.add("Carta d'identita'");
			tipi.add("Patente");
			
			model.addAttribute("listTipi", tipi);
			
			return "inserisci_dati";
		}
		else return "redirect:/login";
	}
	
	@PostMapping("/effettua_prenotazione/inserisci_dati")
	public String insertDati(Model model, CorrierePrenotazioneDto prenotazioneDto) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null && auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("CORRIERE"))) {
					
			if (prenotazioneService.prenotazione_already_exists(prenotazioneDto)) {
				return "redirect:/home_corriere?alreadyBooked";
			}
		
			prenotazioneDto.setChiavePrenotazione(prenotazioneDto.getChiavePrenotazione());
			
			prenotazioneDto.setOrario(prenotazioneDto.getOrario());
			
			prenotazioneService.createPrenotazione(prenotazioneDto);
			
			return "redirect:/home_corriere/visualizza_prenotazioni/"+prenotazioneDto.getChiavePrenotazione();
			
		}
		else return "redirect:/login"; 
	}
	
	@GetMapping(value = "/visualizza_prenotazioni/{qrcode}",produces = MediaType.IMAGE_PNG_VALUE)
	public ResponseEntity<BufferedImage> qrgenQRCode(@PathVariable String qrcode) throws Exception {
        return okResponse(QRCodeService.generateQRCodeImage(qrcode));
    }

    private ResponseEntity<BufferedImage> okResponse(BufferedImage image) {
        return new ResponseEntity<>(image, HttpStatus.OK);
    }
    
	
	@GetMapping("/gestione-ordini")
	public String showGestioneOrdini(Model model) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null && auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("CORRIERE"))) {
			
			Utente u=userService.loadUserByMail(auth.getName());
			
			ArrayList<ViewOrdine> ordiniView = new ArrayList<>();
			
			List<Prenotazione> prenotazioniCorriere = prenotazioneService.getPrenotazioniFromIdcorriere(u.getId());
			
			for(Prenotazione p : prenotazioniCorriere) {
				if(p.getOrdineCarico()!=null) {
					
					ordiniView.add(new ViewOrdine("CARICO",p.getOrdineCarico().getNumero(),p.getOrdineCarico().getDataPrevista(),p.getOrdineCarico().getNumeroColli(),p.getOrdineCarico().getNumeroColonne(),p.getOrdineCarico().getNumeroPedane(),p.getOrdineCarico().getPesoTotale(),p.getOrdineCarico().getDeposito().getIndirizzo(),p.getTargaCorriere(),p.getNomeCorriere() + " " + p.getCognomeCorriere()));
				}
				else {
					ordiniView.add(new ViewOrdine("SCARICO",p.getOrdineScarico().getNumero(),p.getOrdineScarico().getDataPrevista(),p.getOrdineScarico().getNumeroColli(),p.getOrdineScarico().getNumeroColonne(),p.getOrdineScarico().getNumeroPedane(),p.getOrdineScarico().getPesoTotale(),p.getOrdineScarico().getDeposito().getIndirizzo(),p.getTargaCorriere(),p.getNomeCorriere() + " " + p.getCognomeCorriere()));
				}
			}
			
			model.addAttribute("listOrdiniView", ordiniView);
			
			return "gestione-ordini-corriere";	
		}
		else return "redirect:/login";
		
		
		
	}
	
	
}