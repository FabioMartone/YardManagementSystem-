package com.sad.yardmanagementsystem.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sad.yardmanagementsystem.service.UtenteService;
import com.sad.yardmanagementsystem.controller.dto.DepositoInfoDto;
import com.sad.yardmanagementsystem.controller.dto.UtenteRegistrationDto;


@Controller
@RequestMapping("/add_orario")
public class GestoreAddOrarioController {

	private UtenteService utenteService;

	public GestoreAddOrarioController(UtenteService userService) {
		super();
		this.utenteService = userService;
	}
	
	@ModelAttribute("Deposito")
    public DepositoInfoDto depoistoInfoDto() {
        return new DepositoInfoDto();
    }
	
	@GetMapping
	public String showAddOrarioForm() {
		return "add_orario";
	}
	
	@PostMapping
	public String add_orario_gestore(@ModelAttribute("Deposito") DepositoInfoDto depositoDto) {
		
		utenteService.add_orario_disp(depositoDto);
		return "redirect:/add_orario?success";
		
	}
}
