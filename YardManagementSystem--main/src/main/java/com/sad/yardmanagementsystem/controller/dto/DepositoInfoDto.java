package com.sad.yardmanagementsystem.controller.dto;
   
import com.sad.yardmanagementsystem.model.OrarioDisponibile;

public class DepositoInfoDto {
	
	private Long id;
	private String orario;
	
	public DepositoInfoDto() {
		super();
	}
	
	public DepositoInfoDto(Long id, String orario) {
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
	public String getorario() {
		return orario;
	}
	public void setorario(String orario) {
		this.orario = orario;
	}

 
}