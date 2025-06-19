package com.viajando.domain;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

public class Vuelo {

	private int id;
	private Destino destino;
	private LocalDate ida;
	private LocalDate vuelta;
	private int precio;
	private int estrellas;
	private LocalTime hora_ida;
	private LocalTime hora_vuelta;

	public Vuelo(int id, String nombreDestino, LocalDate ida, LocalDate vuelta, int precio, int estrellas, LocalTime hora_ida, LocalTime hora_vuelta) {
		
		this.id = id;
		
		Destino destino = new Destino();//Revisarr
	    destino.setNombre(nombreDestino);
	    this.destino = destino;
		
		this.ida = ida;
	    this.vuelta = vuelta;
	    this.precio = precio;
		this.estrellas = estrellas;
	    this.hora_ida = hora_ida;
	    this.hora_vuelta = hora_vuelta;
	}
	
	public Vuelo() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Destino getDestino() {
		return destino;
	}

	public void setDestino(Destino destino) {
		this.destino = destino;
	}

	public LocalDate getIda() {
		return ida;
	}

	public void setIda(LocalDate ida) {
		this.ida = ida;
	}

	public LocalDate getVuelta() {
		return vuelta;
	}

	public void setVuelta(LocalDate vuelta) {
		this.vuelta = vuelta;
	}

	public int getPrecio() {
		return precio;
	}

	public void setPrecio(int precio) {
		this.precio = precio;
	}

	public int getEstrellas() {
		return estrellas;
	}

	public void setEstrellas(int estrellas) {
		this.estrellas = estrellas;
	}

	public LocalTime getHora_ida() {
		return hora_ida;
	}

	public void setHora_ida(LocalTime hora_ida) {
		this.hora_ida = hora_ida;
	}

	public LocalTime getHora_vuelta() {
		return hora_vuelta;
	}

	public void setHora_vuelta(LocalTime hora_vuelta) {
		this.hora_vuelta = hora_vuelta;
	}

}
