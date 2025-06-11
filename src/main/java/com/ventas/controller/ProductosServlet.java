package com.ventas.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ventas.dao.producto.ProductoDao;
import com.ventas.dao.producto.ProductoDaoImpl;
import com.ventas.entity.Producto;
import com.ventas.excepciones.MercaditoException;

@WebServlet(urlPatterns = { "/productos"})
public class ProductosServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ProductoDao productosDao = new ProductoDaoImpl();

	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/producto.jsp");
	
		try {
			
			List<Producto> productosPortada = new ArrayList<Producto>();  
			productosPortada = productosDao.list();
			
			HttpSession misession= req.getSession(true);	

			if(misession.getAttribute("carrito") != null) {
				List<Producto> pedidos	=(List<Producto>) misession.getAttribute("carrito");
				req.setAttribute("carrito", pedidos);
			}
			
			req.setAttribute("productos", productosPortada);
			
			
		} catch (MercaditoException e) {
				// TODO Auto-generated catch blo
		
			
		}
		dispatcher.forward(req, resp);
	}



}
