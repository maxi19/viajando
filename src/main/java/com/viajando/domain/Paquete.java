package com.viajando.domain;

public class Paquete implements Reservable {

    private int id;
    private String nombre;
    private String descripcion;
    private int precio;
    private double estrellas;
    private int hotel_id;
    private int vuelo_id;
    private int excursion_id;
    private int personas; // si usás el campo `personas` en la tabla

    // Constructor con todos los campos
    public Paquete(int id, String nombre, String descripcion,
                   int hotel_id, int vuelo_id, int excursion_id,
                   double estrellas, int personas, int precio) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.hotel_id = hotel_id;
        this.vuelo_id = vuelo_id;
        this.excursion_id = excursion_id;
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

    public int getHotel_id() {
        return hotel_id;
    }

    public void setHotel_id(int hotel_id) {
        this.hotel_id = hotel_id;
    }

    public int getVuelo_id() {
        return vuelo_id;
    }

    public void setVuelo_id(int vuelo_id) {
        this.vuelo_id = vuelo_id;
    }

    public int getExcursion_id() {
        return excursion_id;
    }

    public void setExcursion_id(int excursion_id) {
        this.excursion_id = excursion_id;
    }

    public int getPersonas() {
        return personas;
    }

    public void setPersonas(int personas) {
        this.personas = personas;
    }

    // Métodos de la interfaz Reservable

    @Override
    public String dameTipò() {
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
}
