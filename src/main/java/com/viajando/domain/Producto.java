package com.viajando.domain;


public class Producto {

	private int id;
	private int precio;
	private String img;
	private String descripcion;
	
	public Producto(int id, int precio, String img, String descripcion) {
		super();
		this.id = id;
		this.precio = precio;
		this.img = img;
		this.descripcion = descripcion;
	}
	
	public Producto(int precio, String img, String descripcion) {
		super();
		this.precio = precio;
		this.img = img;
		this.descripcion = descripcion;
	}
	
}
