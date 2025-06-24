package com.viajando.domain;

import java.util.ArrayList;
import java.util.List;

public class Carrito {

	private List<Reservable> reservables;
	private int total ;
	
	public Carrito() {
		this.reservables = new ArrayList<>();
		this.total = 0;
	}

	public List<Reservable> getReservables() {
		return reservables;
	}

	public void setReservables(List<Reservable> reservables) {
		this.reservables = reservables;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}
	
	
	
}
