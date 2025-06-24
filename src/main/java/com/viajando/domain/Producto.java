package com.viajando.domain;


public class Producto extends GenericEntity{

	private int id;
	private int precio;
	private String img;
	private String descripcion;
	
	public Producto(int id, int precio, String img, String descripcion) {
		super(id);
		this.precio = precio;
		this.img = img;
		this.descripcion = descripcion;
	}
	

}
