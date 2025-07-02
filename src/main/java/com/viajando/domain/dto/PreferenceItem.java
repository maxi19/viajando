package com.viajando.domain.dto;

import java.io.Serializable;

public class PreferenceItem implements Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	private String name;
    private Integer quantity;
    private Float price;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public Float getPrice() {
		return price;
	}
	public void setPrice(Float price) {
		this.price = price;
	}
    
    
}
