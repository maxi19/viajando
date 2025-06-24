package com.viajando.domain;

import java.time.LocalDate;


public class Tours extends Producto{

	private String destino;
	private String pais;
	private LocalDate checkIn;
	private LocalDate checkOut;
	
	public Tours(int id,  int precio, String img, String descripcion) {
		super(id, precio, img, descripcion);
	}
	
	
}
