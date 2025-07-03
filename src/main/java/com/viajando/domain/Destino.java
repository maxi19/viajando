package com.viajando.domain;

public class Destino extends GenericEntity {
	
    private String nombre;
    private String pais;
    private int precio;
    
    
    // Constructor vacío para poder hacer new Destino()
    public Destino() {
        super(0);  // Obligatorio llamar al constructor padre
    }
    
	public Destino(int id) {
		super(id);
	}
	
	public Destino(int id, String nombre, String pais, int precio) {
		super(id);
		this.nombre = nombre;
		this.pais = pais;
		this.precio = precio;
	}

	
    public int getId() {
		return super.getId();
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
