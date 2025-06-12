package com.viajando.domain;

import java.time.LocalDate;

public class Hotel extends Producto{
	
	private LocalDate checkIn;
	private LocalDate checkOut;

	public Hotel(int id, int precio, String img, String descripcion) {
		super(id, precio, img, descripcion);

	
	}
	public LocalDate getCheckIn() {
		return checkIn;
	}
	public void setCheckIn(LocalDate checkIn) {
		this.checkIn = checkIn;
	}
	public LocalDate getCheckOut() {
		return checkOut;
	}
	public void setCheckOut(LocalDate checkOut) {
		this.checkOut = checkOut;
	}
	
	
}
