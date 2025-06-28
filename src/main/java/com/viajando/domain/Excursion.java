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
	    private int cantidadPersonas = 1;

	    public Excursion(int id, String nombre, String descripcion, LocalDate fecha_inicio, LocalDate fecha_fin, int precio, Destino destino, double estrellas, String imagen) {
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

	    // Getters y Setters
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

	    public Destino getDestino() {
	        return destino;
	    }

	    public void setDestino(Destino destino) {
	        this.destino = destino;
	    }

	    // Métodos de la interfaz Reservable
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
	        return "Excursion";
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