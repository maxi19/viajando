package com.viajando.domain;

import java.time.LocalDate;


public class Tours extends Producto{

	private String destino;
	private String pais;
	private LocalDate checkIn;
	private LocalDate checkOut;
	
	public Tours(int precio, String img, String descripcion) {
		super(precio, img, descripcion);
		// TODO Auto-generated constructor stub
	}
	
	
}
