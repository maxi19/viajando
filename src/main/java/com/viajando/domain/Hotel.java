package com.viajando.domain;

import java.time.LocalDate;
import java.time.LocalTime;

public class Hotel {
	
	private int id;
	private String nombre;
	private int destino_id;
	private String destino_value;
	private double estrellas;
	private int precio;
	private String imagen;
	
	public Hotel(int id, String nombre, int destino_id, String destino_value, double estrellas, int precio,
			String imagen) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.destino_id = destino_id;
		this.destino_value = destino_value;
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

	public int getDestino_id() {
		return destino_id;
	}

	public void setDestino_id(int destino_id) {
		this.destino_id = destino_id;
	}

	public String getDestino_value() {
		return destino_value;
	}

	public void setDestino_value(String destino_value) {
		this.destino_value = destino_value;
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
