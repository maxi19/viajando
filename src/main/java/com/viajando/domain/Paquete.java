package com.viajando.domain;

public class Paquete implements Reservable {

    private int id;
    private String nombre;
    private String descripcion;
    private Hotel hotel;
    private Vuelo vuelo;
    private Excursion excursion;
    private double estrellas;
    private int personas; // si usás el campo `personas` en la tabla
    private int precio;
    
    // Constructor con todos los campos
    public Paquete(int id, String nombre, String descripcion, Hotel hotel, Vuelo vuelo, Excursion excursion,
			double estrellas, int personas, int precio) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.hotel = hotel;
		this.vuelo = vuelo;
		this.excursion = excursion;
		this.estrellas = estrellas;
		this.personas = personas;
		this.precio = precio;
	}

    
    // Getters y setters

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

    public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}

	public Vuelo getVuelo() {
		return vuelo;
	}

	public void setVuelo(Vuelo vuelo) {
		this.vuelo = vuelo;
	}

	public Excursion getExcursion() {
		return excursion;
	}

	public void setExcursion(Excursion excursion) {
		this.excursion = excursion;
	}

	public int getPersonas() {
        return personas;
    }

    public void setPersonas(int personas) {
        this.personas = personas;
    }

    // Métodos de la interfaz Reservable

    @Override
    public String dameTipo() {
        return "Paquete";
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
	public int getCantidadPersonas() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setCantidadPersonas(int cantidad) {
		// TODO Auto-generated method stub
		
	}
}
