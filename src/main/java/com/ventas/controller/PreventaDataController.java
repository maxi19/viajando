package com.ventas.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.ventas.dao.producto.ProductoDao;
import com.ventas.dao.producto.ProductoDaoImpl;
import com.ventas.dto.PedidoDto;
import com.ventas.entity.Item;
import com.ventas.entity.Producto;
import com.ventas.excepciones.MercaditoException;

@WebServlet(urlPatterns = { "/preventaData"})
public class PreventaDataController extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ProductoDao dao =  new ProductoDaoImpl();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Producto prod = null;
		Item item =  null;
		//String method = (String)req.getParameter("method");
		int idProd=0;
		boolean soloProducto= false;
		HttpSession misession= req.getSession(true);	
		List<Item> items= null;
		PedidoDto pedidoDto = new PedidoDto();
		/*
		if ( method != null  &&  "unidad".equals(req.getParameter("method")) ) {
			String idProductoStr =(String) req.getParameter("idproducto");		
			idProd = Integer.parseInt(idProductoStr);
			item = obtenerItem(item, idProd);
			items =Arrays.asList(item);
			soloProducto = true;
		}else if ( method != null  &&  "listado".equals(req.getParameter("method")) ){
			if (misession.getAttribute("items") != null) {
				items =(List<Item>)misession.getAttribute("items");
				soloProducto = false;
				req.setAttribute("soloProducto", false);
			}
			
		}
		*/
		//misession.setAttribute("items", items);
		//misession.setAttribute("soloProducto",soloProducto);
		//req.setAttribute("soloProducto",soloProducto);
		//req.setAttribute("items", items);
		//req.setAttribute("idProducto", idProd);
		items =(List<Item>)misession.getAttribute("items");
		pedidoDto.setItems(items);
		pedidoDto.setTotal(0);
		
		Gson json = new Gson();
		PrintWriter out = resp.getWriter();
		resp.setContentType("application/json");
		resp.setCharacterEncoding("utf-8");
		out.print(json.toJson(pedidoDto).toString());
		out.flush();
		
		
		

	}

	private Item obtenerItem(Item item, int idProd) {
		Producto prod;
		try {
			 prod =	dao.getOne(idProd);
			 item = new Item(prod);
		} catch (MercaditoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return item;
	}
	
	
}
