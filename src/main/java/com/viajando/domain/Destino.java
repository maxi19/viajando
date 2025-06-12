package com.viajando.domain;

public class Destino {
	
	//haceer alta de destinos y listado de destino
	//hacer listado de paises
	private int id;
    private String nombre;
    private String pais;
    private int precio;
	
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
	public String getPais() {
		return pais;
	}
	public void setPais(String pais) {
		this.pais = pais;
	}
	public int getPrecio() {
		return precio;
	}
	public void setPrecio(int precio) {
		this.precio = precio;
	}
    
}
