package com.viajando.domain;

import java.time.LocalDate;
import java.time.LocalTime;

public class Vuelo extends GenericEntity implements Reservable{

	private int id;
	private String destino;
	private LocalDate ida;
	private LocalDate vuelta;
	private int precio;
	private double estrellas;
	private LocalTime horaIda;
	private LocalTime horaVuelta;

	public Vuelo(int id, String destino, LocalDate ida, LocalDate vuelta, int precio, double estrellas, LocalTime horaIda, LocalTime horaVuelta) {
		super(id);
		this.destino = destino;
		this.ida = ida;
		this.vuelta = vuelta;
		this.precio = precio;
		this.estrellas = estrellas;
		this.horaIda = horaIda;
		this.horaVuelta = horaVuelta;
	}

	public int getId() { return id; }
	public void setId(int id) { this.id = id; }

	public String getDestino() { return destino; }
	public void setDestino(String destino) { this.destino = destino; }

	public LocalDate getIda() { return ida; }
	public void setIda(LocalDate ida) { this.ida = ida; }

	public LocalDate getVuelta() { return vuelta; }
	public void setVuelta(LocalDate vuelta) { this.vuelta = vuelta; }

	public int getPrecio() { return precio; }
	public void setPrecio(int precio) { this.precio = precio; }

	public double getEstrellas() { return estrellas; }
	public void setEstrellas(double estrellas) { this.estrellas = estrellas; }

	public LocalTime getHoraIda() { return horaIda; }
	public void setHoraIda(LocalTime horaIda) { this.horaIda = horaIda; }

	public LocalTime getHoraVuelta() { return horaVuelta; }
	public void setHoraVuelta(LocalTime horaVuelta) { this.horaVuelta = horaVuelta; }

	@Override
	public String dameTipò() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int dameId() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int damePrecio() {
		// TODO Auto-generated method stub
		return 0;
	}

}
