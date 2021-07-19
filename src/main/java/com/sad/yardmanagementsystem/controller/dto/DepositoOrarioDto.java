package com.sad.yardmanagementsystem.controller.dto;

public class DepositoOrarioDto {
	
	private Long id;
	private String orario;
	
	public DepositoOrarioDto() {
		super();
	}
	
	public DepositoOrarioDto(Long id, String orario) {
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
	public String getOrario() {
		return orario;
	}
	public void setOrario(String orario) {
		this.orario = orario;
	}

 
}