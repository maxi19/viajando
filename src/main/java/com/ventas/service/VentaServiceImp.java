package com.ventas.service;

import java.util.List;

import com.ventas.dao.venta.VentasDao;
import com.ventas.dao.venta.VentasDaoImpl;
import com.ventas.entity.Contacto;
import com.ventas.entity.Item;
import com.ventas.excepciones.MercaditoException;

public class VentaServiceImp implements VentaService{

	private VentasDao ventasDao = new VentasDaoImpl();
	
	@Override
	public void procesarVenta(List<Item> items, Contacto contacto) throws MercaditoException {
		ventasDao.registrarTransaccion(items, new VentaBuilderImp(), contacto);
	}
	
}
