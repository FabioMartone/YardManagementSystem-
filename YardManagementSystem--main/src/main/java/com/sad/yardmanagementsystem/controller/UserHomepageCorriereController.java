package com.sad.yardmanagementsystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sad.yardmanagementsystem.model.Deposito;
import com.sad.yardmanagementsystem.repository.RepositoryDeposito;
import com.sad.yardmanagementsystem.service.UtenteService;


@Controller
@RequestMapping("/home_corriere")
public class UserHomepageCorriereController {

	private UtenteService userService;
	
	@Autowired
	private RepositoryDeposito repositoryDeposito;
	
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
	public String showGestioneAssociazioni(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null && auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("CORRIERE"))) {
			
			List<Deposito> depositi = repositoryDeposito.findAll();
			// dobbiamo mostrare solo i depositi non associati ancora al corriere
			model.addAttribute("listDepositi",depositi);
			
			
			
			return "gestione-associazioni";
		}
		else return "redirect:/login"; //creare eccezione apposita
	}
	
	
}