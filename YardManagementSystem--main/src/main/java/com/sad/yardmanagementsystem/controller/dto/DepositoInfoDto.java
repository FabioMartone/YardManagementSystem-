package com.sad.yardmanagementsystem.controller.dto;
 
import java.util.List; 
import com.sad.yardmanagementsystem.model.OrarioDisponibile;

public class DepositoInfoDto {
	
	private Long id;
	private OrarioDisponibile orario;
	
	public DepositoInfoDto() {
		super();
	}
	
	public DepositoInfoDto(Long id, OrarioDisponibile orario) {
		super();
		this.id = id;
		this.orario = orario;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public OrarioDisponibile getorario() {
		return orario;
	}
	public void setorario(OrarioDisponibile orario) {
		this.orario = orario;
	}

 
}