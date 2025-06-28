package com.viajando.domain;

public interface Reservable {

	public String dameTipo();
	

    public int dameId();

    public int damePrecio();

    int getCantidadPersonas();

    void setCantidadPersonas(int cantidad);
	
	
}
