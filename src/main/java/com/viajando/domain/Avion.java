package com.viajando.domain;

public class Avion extends GenericEntity {
	
	private String nombre;
    private int empresaId;
    private int capacidad;

	public Avion(int id) {
		super(id);
		// TODO Auto-generated constructor stub
	}
	

    public Avion(int id, String nombre, int empresaId, int capacidad) {
        super(id);
        this.nombre = nombre;
        this.empresaId = empresaId;
        this.capacidad = capacidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEmpresaId() {
        return empresaId;
    }

    public void setEmpresaId(int empresaId) {
        this.empresaId = empresaId;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

}
