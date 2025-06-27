package com.viajando.domain;

import java.time.LocalDate;

public class Excursion implements Reservable {

	private int id;
	private String nombre;
	private String descripcion;
	private LocalDate fecha_inicio;
	private LocalDate fecha_fin;
	private int precio;
	private Destino destino;
	private double estrellas;
	private String imagen;
	private int cantidadPersonas = 1; // valor por defecto
	
	public Excursion(int id, String nombre, String descripcion, LocalDate fecha_inicio, LocalDate fecha_fin, int precio, Destino destino, double estrellas, String imagen) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.fecha_inicio = fecha_inicio;
		this.fecha_fin = fecha_fin;
		this.precio = precio;
		this.destino = destino;
		this.estrellas = estrellas;
		this.imagen = imagen;
	}

	public Destino getDestino() {
		return destino;
	}
	
	public void setDestino(Destino destino) {
		this.destino = destino;
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

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
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

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

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
	public int getCantidadPersonas() {
		return cantidadPersonas;
	}

	public void setCantidadPersonas(int cantidadPersonas) {
		this.cantidadPersonas = cantidadPersonas;
	}
	
	
	
}