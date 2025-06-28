package com.viajando.domain;

import java.time.LocalDate;
import java.time.LocalTime;

public class Hotel {
	
	private int id;
	private String nombre;
	private Destino destino;
	private double estrellas;
	private int precio;
	private String imagen;
	
	public Hotel(int id, String nombre, Destino destino, double estrellas, int precio,
			String imagen) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.destino = destino;
		this.estrellas = estrellas;
		this.precio = precio;
		this.imagen = imagen;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Destino getDestino_id() {
		return destino;
	}

	public void setDestino_id(Destino destino) {
		this.destino = destino;
	}


	public double getEstrellas() {
		return estrellas;
	}

	public void setEstrellas(double estrellas) {
		this.estrellas = estrellas;
	}

	public int getPrecio() {
		return precio;
	}

	public void setPrecio(int precio) {
		this.precio = precio;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}
	

	

	
	
}
