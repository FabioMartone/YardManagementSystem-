package com.sad.yardmanagementsystem.controller;

import java.util.ArrayList;    
import java.util.List;

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

import com.sad.yardmanagementsystem.controller.dto.AreaDto;
import com.sad.yardmanagementsystem.controller.dto.CorriereMailDto;
import com.sad.yardmanagementsystem.controller.dto.DepositoDto;
import com.sad.yardmanagementsystem.controller.dto.DepositoOrarioDto;
import com.sad.yardmanagementsystem.controller.dto.OrdineCaricoDto;
import com.sad.yardmanagementsystem.controller.dto.OrdineScaricoDto;
import com.sad.yardmanagementsystem.controller.dto.OrdiniCaricoMovimenti;
import com.sad.yardmanagementsystem.controller.dto.OrdiniScaricoMovimenti;
import com.sad.yardmanagementsystem.controller.dto.ViewOrdiniMovimenti;
import com.sad.yardmanagementsystem.model.Area;
import com.sad.yardmanagementsystem.model.DepositiCorrieri;
import com.sad.yardmanagementsystem.model.Deposito;
import com.sad.yardmanagementsystem.model.OrdineCarico;
import com.sad.yardmanagementsystem.model.OrdineScarico;
import com.sad.yardmanagementsystem.model.TipoArea;
import com.sad.yardmanagementsystem.model.Utente;
import com.sad.yardmanagementsystem.repository.RepositoryArea;
import com.sad.yardmanagementsystem.repository.RepositoryDepositiCorrieri;
import com.sad.yardmanagementsystem.repository.RepositoryDeposito;
import com.sad.yardmanagementsystem.repository.RepositoryOrarioDisponibile;
import com.sad.yardmanagementsystem.repository.RepositoryOrdineCarico;
import com.sad.yardmanagementsystem.repository.RepositoryOrdineScarico;
import com.sad.yardmanagementsystem.repository.RepositoryUtente;
import com.sad.yardmanagementsystem.service.EmailService;
import com.sad.yardmanagementsystem.service.OrdineService;
import com.sad.yardmanagementsystem.service.RandomStringGenerator;
import com.sad.yardmanagementsystem.service.UtenteService;
import com.sad.yardmanagementsystem.service.AreaService;
import com.sad.yardmanagementsystem.service.DepositoService;


@Controller
@RequestMapping("/home_gestore")
public class UserHomepageGestoreController {

	private UtenteService userService;	
	@Autowired
	private DepositoService depositoService;
	@Autowired
	private RepositoryDeposito repositoryDeposito;
	@Autowired
	private RepositoryUtente utenteRepository;
	@Autowired 
	private RepositoryDepositiCorrieri repositoryDepositiCorrieri;
	@Autowired 
	private EmailService emailService;
	@Autowired 
	private OrdineService ordineService;
	@Autowired 
	private RepositoryOrarioDisponibile repOrario;
	@Autowired
	private AreaService areaService;
	@Autowired
	private RepositoryOrdineCarico repOrdineCarico;
	@Autowired
	private RepositoryOrdineScarico repOrdineScarico;
	@Autowired
	private RepositoryArea repArea;
	
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
	public String showAddOrarioForm(Model model) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null && auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("GESTORE"))) {
			
			Utente u=userService.loadUserByMail(auth.getName());
			
			model.addAttribute("listDeposito", repositoryDeposito.findByid_gestore(u.getId()));
			
			model.addAttribute("listOrario", repOrario.findAll());
			
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
			
			if(!depositoService.deposito_exists(depositoOrarioDto)) {
				return "redirect:/home_gestore/add_orario?error0";
			}
			
			if(depositoService.orario_deposito_exists(depositoOrarioDto)) {
				return "redirect:/home_gestore/add_orario?error1";
			}
			
			if(!depositoService.fascia_oraria_exists(depositoOrarioDto)) {
				return "redirect:/home_gestore/add_orario?error2";
			}
			
			if(!depositoService.is_associated(auth.getName(), depositoOrarioDto)) {
				return "redirect:/home_gestore/add_orario?error3";
			}
			
			depositoService.add_orario_disp(depositoOrarioDto);
			
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
	
	@PostMapping("/add_deposito")
	public String AddDeposito(@ModelAttribute("Deposito") DepositoDto depositoDto, Model model) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null && auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("GESTORE"))) {
		
			List<Deposito> depositi = repositoryDeposito.findAll();
			
			for(Deposito d : depositi) {
				
				if(d.getIndirizzo().equals(depositoDto.getIndirizzo())) {
					return "redirect:/home_gestore/add_deposito?error";
				}
			}
			
			model.addAttribute("indirizzo", depositoDto.getIndirizzo());
					
			return "add_area";
		}else return "redirect:/login"; 
	}

	@GetMapping("/add_deposito/add_area")
	public String showAddAreaForm() {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null && auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("GESTORE"))) {

			return "add_area";	
		}
		else return "redirect:/login";
	}
	
	List<AreaDto> aree = new ArrayList<>();
	
	@PostMapping("/add_deposito/add_area")
	public String AddArea(@ModelAttribute("Deposito") DepositoDto depositoDto, Model model) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null && auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("GESTORE"))) {
			
			TipoArea t = TipoArea.LAVORO;
			
			if(depositoDto.getTipo().equals("LAVORO")) {
				t=TipoArea.LAVORO;
			}
			else if(depositoDto.getTipo().equals("SOSTA")){
				t=TipoArea.SOSTA;
			}
			
			aree.add(new AreaDto(depositoDto.getDescrizione(), 0, t));
			
			model.addAttribute("indirizzo", depositoDto.getIndirizzo());
			
			return "success";
			
		}else return "redirect:/login";
	}
	
	@PostMapping("/add_deposito/add_area/success")
	public String Success(@ModelAttribute("Deposito") DepositoDto depositoDto, Model model) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null && auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("GESTORE"))) {
			
			model.addAttribute("indirizzo", depositoDto.getIndirizzo());
			
			TipoArea t = TipoArea.LAVORO;
			
			if(depositoDto.getTipo().equals("LAVORO")) {
				t=TipoArea.LAVORO;
			}
			else if(depositoDto.getTipo().equals("SOSTA")){
				t=TipoArea.SOSTA;
			}
			
			aree.add(new AreaDto(depositoDto.getDescrizione(), 0, t));
			
			model.addAttribute("indirizzo", depositoDto.getIndirizzo());
			
			return "success";
			
		}else return "redirect:/login";
	}
	
	@PostMapping("/add_deposito/add_area/inserisci_deposito")
	public String InserisciDeposito(@ModelAttribute("Deposito") DepositoDto depositoDto, Model model) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null && auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("GESTORE"))) {
			
			Utente gestore = utenteRepository.findByEmail(auth.getName());
			
			Deposito deposito = depositoService.add_deposito(depositoDto.getIndirizzo(), gestore);
			
			List<Area> aree_deposito = new ArrayList<>();
			
			for(AreaDto a : aree) {
				Area area = new Area(a.getDescrizione(), a.getFlag(), a.getTipo(), deposito);
				repArea.save(area);
				aree_deposito.add(area);
				areaService.add_area(area);
			}
			
			deposito.setAree(aree_deposito);
			
			return "redirect:/home_gestore/visualizza_depositi?success";
			
		}else return "redirect:/login";
	}
	
	@GetMapping("/visualizza_depositi")
	public String showDepositi(Model model, DepositoDto depositoDto) {
		
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
	
	@GetMapping("/gestione-ordini")
	public String showGestioneOrdini(Model model) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null && auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("GESTORE"))) {
			
			Utente u=userService.loadUserByMail(auth.getName());
			
			
			model.addAttribute("listDeposito", repositoryDeposito.findByid_gestore(u.getId()));
			
			return "gestione-ordini";	
		}
		else return "redirect:/login";
		
		
	}
	
	@ModelAttribute("ordineCarico")
    public OrdineCaricoDto ordineCaricoDto() {
        return new OrdineCaricoDto();
        
    }
	
	@ModelAttribute("ordineScarico")
    public OrdineScaricoDto ordineScaricoDto() {
        return new OrdineScaricoDto();
        
    }
	
	@ModelAttribute("corriereMail")
    public CorriereMailDto corriereMailDto() {
        return new CorriereMailDto();
        
    }
	
	@PostMapping("/gestione-ordini/inserisciOrdineCarico")
	public String AddOrdineCarico(@ModelAttribute("ordineCarico") OrdineCaricoDto ordineCaricoDto,Model model) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null && auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("GESTORE"))) {
			
			String chiavePrenotazione=RandomStringGenerator.generateString(ordineCaricoDto.getNumero());

			ordineService.add_ordine_carico(ordineCaricoDto,chiavePrenotazione);
			
			List<Utente> corr_ass = repositoryDepositiCorrieri.findByIdDeposito(ordineCaricoDto.getDeposito());
			
			model.addAttribute("listCorrieri",corr_ass);
			model.addAttribute("chiave_prenotazione",chiavePrenotazione);
			
			return "gestione-ordini-email-corriere";
			
		}else return "redirect:/login";
	}

	@PostMapping("/gestione-ordini/gestione-ordini-email-corriere")
	public String sendMailCorriere(@ModelAttribute("corriereMail") CorriereMailDto corriereMailDto) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null && auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("GESTORE"))) {
			
			
			Utente u = userService.loadUserById(corriereMailDto.getCorriere());
			Utente g = userService.loadUserByMail(auth.getName());
		    
			emailService.sendSimpleMessage(u.getEmail(), "Nuova richiesta Ordine", "Gentile "+u.getRagioneSociale()+", c'Ã¨ una richiesta di ordine da parte del gestore " + g.getRagioneSociale()+"\nUsa la seguente chiave per prenotarti: "+corriereMailDto.getChiave_prenotazione()+"\nCordiali saluti" );
			
		    return "redirect:/home_gestore/gestione-ordini?successCarico";
		}
		else return "redirect:/login"; 
	}
	
	@PostMapping("/gestione-ordini/inserisciOrdineScarico")
	public String AddOrdineScarico(@ModelAttribute("ordineScarico") OrdineScaricoDto ordineScaricoDto, Model model) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null && auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("GESTORE"))) {
			
			String chiavePrenotazione=RandomStringGenerator.generateString(ordineScaricoDto.getNumero());
			
			ordineService.add_ordine_scarico(ordineScaricoDto,chiavePrenotazione);
			
			emailService.sendSimpleMessage(ordineScaricoDto.getMailFornitore(), "Nuovo Ordine Scarico "+ordineScaricoDto.getNumero(), "Gentile "+ordineScaricoDto.getRagioneSocialeFornitore()+", le inviamo il Codice di Prenotazione, da inviare al Corriere scelto per la spedizione. Ricordiamo che quest'ultimo deve essere registrato sulla nostra Piattaforma Online.\nCodice Prenotazione: "
			+chiavePrenotazione+"\nCordiali Saluti.");
			
			return "redirect:/home_gestore/gestione-ordini?successScarico";
			
		}else return "redirect:/login";
	}
	
	@GetMapping("/visualizza_ordini")
	public String showOrdiniCarico() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null && auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("GESTORE"))) {
		    
		    return "visualizza_ordini";
		}
		else return "redirect:/login"; 
	}
	
	
	@GetMapping("/visualizza_ordini/visualizza_ordini_carico")
	public String showOrdiniCarico(Model model) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null && auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("GESTORE"))) {
			
			ViewOrdiniMovimenti view = new ViewOrdiniMovimenti();
		    
	  	    List<OrdiniCaricoMovimenti> listaCarico = new ArrayList<>();
		    
		    List<OrdineCarico> ordiniCarico = repOrdineCarico.findByEmailGestore(auth.getName());
		
		    for (OrdineCarico oc : ordiniCarico) {
		    	if (oc.getPrenotazione()!=null) {
		    		listaCarico.add(new OrdiniCaricoMovimenti(oc,oc.getPrenotazione().getMovimenti()));
		    	}
		    	else {
		    		listaCarico.add(new OrdiniCaricoMovimenti(oc,new ArrayList<>()));
		    	}
		    }
		    
		    view.setOrdiniCaricoMovimenti(listaCarico);
		    
		    model.addAttribute("View",view);
		    
		    return "visualizza_ordini_carico";
		}
		else return "redirect:/login"; 
	}
	
	
	@GetMapping("/visualizza_ordini/visualizza_ordini_scarico")
	public String showOrdiniScarico(Model model) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null && auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("GESTORE"))) {
			
			ViewOrdiniMovimenti view = new ViewOrdiniMovimenti();
		    
		    List<OrdiniScaricoMovimenti> listaScarico = new ArrayList<>();
		    
		    List<OrdineScarico> ordiniScarico = repOrdineScarico.findByEmailGestore(auth.getName());
		    
		    for (OrdineScarico os: ordiniScarico) {
		    	if (os.getPrenotazione()!=null) {
		    		listaScarico.add(new OrdiniScaricoMovimenti(os,os.getPrenotazione().getMovimenti()));
		    	}
		    	else {
		    		listaScarico.add(new OrdiniScaricoMovimenti(os,new ArrayList<>()));
		    	}
		    }
		    
		    view.setOrdiniScaricoMovimenti(listaScarico);
		    
		    model.addAttribute("View",view);
		    
		    return "visualizza_ordini_scarico";
		}
		else return "redirect:/login"; 
	}
}