package com.sad.yardmanagementsystem.controller.dto;

import java.util.List;

public class ViewOrdiniMovimenti {
	
	public ViewOrdiniMovimenti(List<OrdiniCaricoMovimenti> ordiniCaricoMovimenti,
			List<OrdiniScaricoMovimenti> ordiniScaricoMovimenti) {
		super();
		this.ordiniCaricoMovimenti = ordiniCaricoMovimenti;
		this.ordiniScaricoMovimenti = ordiniScaricoMovimenti;
	}

	private List<OrdiniCaricoMovimenti> ordiniCaricoMovimenti;
	private List<OrdiniScaricoMovimenti> ordiniScaricoMovimenti;

	public ViewOrdiniMovimenti() {
		// TODO Auto-generated constructor stub
	}

	public List<OrdiniCaricoMovimenti> getOrdiniCaricoMovimenti() {
		return ordiniCaricoMovimenti;
	}

	public void setOrdiniCaricoMovimenti(List<OrdiniCaricoMovimenti> ordiniCaricoMovimenti) {
		this.ordiniCaricoMovimenti = ordiniCaricoMovimenti;
	}

	public List<OrdiniScaricoMovimenti> getOrdiniScaricoMovimenti() {
		return ordiniScaricoMovimenti;
	}

	public void setOrdiniScaricoMovimenti(List<OrdiniScaricoMovimenti> ordiniScaricoMovimenti) {
		this.ordiniScaricoMovimenti = ordiniScaricoMovimenti;
	}

}
