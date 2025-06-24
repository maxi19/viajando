package com.viajando.domain;


public class Hotel extends Producto implements Reservable{
	
	public Hotel(int id, int precio, String img, String descripcion) {
		super(id, precio, img, descripcion);
		// TODO Auto-generated constructor stub
	}

	private String nombre;

	@Override
	public String dameTipò() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int dameId() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int damePrecio() {
		// TODO Auto-generated method stub
		return 0;
	}

	
	
}
