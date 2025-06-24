package com.viajando.domain;

public class Paquete extends Producto {
	
	public Paquete(int id, int precio, String img, String descripcion) {
		super(id, precio, img, descripcion);
	}

	//armar en base paquetes prestablecidos y el 
	// que cliente pueda armar sus propios paquetes
	private int id;
	private String nombre;
	private int dias;
	
	private Hotel hotel;

	private Vuelo vueloIda;
	
	private Vuelo vueloVuelta;	
	
	
}
