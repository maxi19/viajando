package com.viajando.domain;

import java.time.LocalDate;

public class Vuelo {

	//hacer alta de vuelos y listado de vuelos
	private LocalDate fecha_salida;
	private LocalDate fecha_llegada;
	private Destino salida;
	private Destino destino;
	private int precio;
	
	
	public LocalDate getFecha_salida() {
		return fecha_salida;
	}
	public void setFecha_salida(LocalDate fecha_salida) {
		this.fecha_salida = fecha_salida;
	}
	public LocalDate getFecha_llegada() {
		return fecha_llegada;
	}
	public void setFecha_llegada(LocalDate fecha_llegada) {
		this.fecha_llegada = fecha_llegada;
	}
	public Destino getSalida() {
		return salida;
	}
	public void setSalida(Destino salida) {
		this.salida = salida;
	}
	public Destino getDestino() {
		return destino;
	}
	public void setDestino(Destino destino) {
		this.destino = destino;
	}
	public int getPrecio() {
		return precio;
	}
	public void setPrecio(int precio) {
		this.precio = precio;
	}
	

}
