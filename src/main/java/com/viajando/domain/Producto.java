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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPrecio() {
		return precio;
	}

	public void setPrecio(int precio) {
		this.precio = precio;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	

}
