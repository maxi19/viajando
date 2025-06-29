package com.viajando.domain;

import java.time.LocalDate;
import java.time.LocalTime;

public class Hotel implements Reservable{
	
	private int id;
	private String nombre;
	private Destino destino;
	private double estrellas;
	private int precio;
	private String imagen;
    private int cantidadPersonas = 1;
    private int stock;

	
	public Hotel(int id, String nombre, Destino destino, double estrellas, int precio,
			String imagen, int stock) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.destino = destino;
		this.estrellas = estrellas;
		this.precio = precio;
		this.imagen = imagen;
		this.stock = stock;

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


	public double getEstrellas() {
		return estrellas;
	}

	public void setEstrellas(double estrellas) {
		this.estrellas = estrellas;
	}

	public int getPrecio() {
		return precio;
	}

	public void setPrecio(int precio) {
		this.precio = precio;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public int getStock() {
	    return stock;
	}

	public void setStock(int stock) {
	    this.stock = stock;
	}

    @Override
    public int dameId() {
        return id;
    }

    @Override
    public int damePrecio() {
        return precio;
    }

    @Override
    public String dameTipo() {
        return "Hotel";
    }

    @Override
    public int getCantidadPersonas() {
        return cantidadPersonas;
    }

    @Override
    public void setCantidadPersonas(int cantidad) {
        this.cantidadPersonas = cantidad;
    }
	

	

	
	
}
