package com.ventas.dto;
import java.util.List;

import com.ventas.entity.Item;
public class PedidoDto {

	private boolean unProducto;
	private List<Item> items;
	private int total = 0;
	public boolean isUnProducto() {
		return unProducto;
	}
	public void setUnProducto(boolean unProducto) {
		this.unProducto = unProducto;
	}

	public List<Item> getItems() {
		return items;
	}
	public void setItems(List<Item> items) {
		this.items = items;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}

	
	
	
}
