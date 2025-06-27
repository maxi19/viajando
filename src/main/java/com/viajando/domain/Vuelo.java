package com.viajando.domain;

import java.time.LocalDate;
import java.time.LocalTime;

public class Vuelo implements Reservable {
	
	private int id;
	private String nombre;
	private Destino destino;
	private LocalDate fecha_inicio;
	private LocalDate fecha_fin;
	private int precio;
	private double estrellas;
	private LocalTime hora_ida;
	private LocalTime hora_vuelta;
	private int id_avion;
	private String imagen;
	private int cantidadPersonas = 1;

	public Vuelo(int id, String nombre, Destino destino, LocalDate fecha_inicio, LocalDate fecha_fin, int precio,
	             double estrellas, LocalTime hora_ida, LocalTime hora_vuelta, int id_avion, String imagen) {
		this.id = id;
		this.nombre = nombre;
		this.destino = destino;
		this.fecha_inicio = fecha_inicio;
		this.fecha_fin = fecha_fin;
		this.precio = precio;
		this.estrellas = estrellas;
		this.hora_ida = hora_ida;
		this.hora_vuelta = hora_vuelta;
		this.id_avion = id_avion;
		this.imagen = imagen;
	}

	// Getters y Setters
	// (puedo pasártelos listos si querés)

	public int getCantidadPersonas() {
		return cantidadPersonas;
	}

	public void setCantidadPersonas(int cantidadPersonas) {
		this.cantidadPersonas = cantidadPersonas;
	}

	@Override
	public String dameTipò() {
		return "vuelo";
	}

	@Override
	public int dameId() {
		return id;
	}

	@Override
	public int damePrecio() {
		return precio;
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

	public Destino getDestino() {
		return destino;
	}

	public void setDestino(Destino destino) {
		this.destino = destino;
	}

	public LocalDate getFecha_inicio() {
		return fecha_inicio;
	}

	public void setFecha_inicio(LocalDate fecha_inicio) {
		this.fecha_inicio = fecha_inicio;
	}

	public LocalDate getFecha_fin() {
		return fecha_fin;
	}

	public void setFecha_fin(LocalDate fecha_fin) {
		this.fecha_fin = fecha_fin;
	}

	public int getPrecio() {
		return precio;
	}

	public void setPrecio(int precio) {
		this.precio = precio;
	}

	public double getEstrellas() {
		return estrellas;
	}

	public void setEstrellas(double estrellas) {
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

	public int getId_avion() {
		return id_avion;
	}

	public void setId_avion(int id_avion) {
		this.id_avion = id_avion;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}
	
	
}