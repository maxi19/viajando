package com.viajando.domain;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

public class Vuelo {

	private int id;
	private Destino destino;
	private Date ida;
	private Date vuelta;
	private int precio;
	private int estrellas;
	private Time hora_ida;
	private Time hora_vuelta;

	public Vuelo(int id, String nombreDestino, Date ida, Date vuelta, int precio, int estrellas, Time hora_ida, Time hora_vuelta) {
		
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

	public Date getIda() {
		return ida;
	}

	public void setIda(Date ida) {
		this.ida = ida;
	}

	public Date getVuelta() {
		return vuelta;
	}

	public void setVuelta(Date vuelta) {
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

	public Time getHora_ida() {
		return hora_ida;
	}

	public void setHora_ida(Time hora_ida) {
		this.hora_ida = hora_ida;
	}

	public Time getHora_vuelta() {
		return hora_vuelta;
	}

	public void setHora_vuelta(Time hora_vuelta) {
		this.hora_vuelta = hora_vuelta;
	}

}