package com.ventas.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.mercadopago.MercadoPago;
import com.mercadopago.exceptions.MPConfException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.Preference;
import com.mercadopago.resources.Preference.AutoReturn;
import com.mercadopago.resources.Preference.OperationType;
import com.mercadopago.resources.datastructures.preference.Item;
import com.ventas.dto.LinkPedido;
import com.mercadopago.resources.datastructures.preference.BackUrls;

@WebServlet(urlPatterns = { "/createAndRedirect"})
public class RedirectController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Preference preference = new Preference();
        try {
			MercadoPago.SDK.setAccessToken("APP_USR-101877887565025-050616-53358a14027355def6ee452d3c44ba55-2353713216");
			
		} catch (MPConfException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		preference.setBackUrls(
				new BackUrls().setFailure("http://localhost:8080/failure")
				.setPending("http://localhost:8080/pending")
				.setSuccess("http://localhost:8080/success")
				);
		//preference.setOperationType(OperationType.regular_payment);
		//preference.setAutoReturn(AutoReturn.all);
		//aca es donde le agrego los productos
			
		HttpSession misession= req.getSession(true);	
		List<com.ventas.entity.Item> itemsEnSession= null;
		itemsEnSession =(List<com.ventas.entity.Item>)misession.getAttribute("items");

		for (com.ventas.entity.Item itemLocal : itemsEnSession) {
			Item item = new Item();
			
	System.out.println(itemLocal.toString());
			item.setTitle(itemLocal.getProducto().getTitulo())
				.setQuantity(itemLocal.getCantidad())
				.setUnitPrice((float) itemLocal.getProducto().getPrecio());
			preference.appendItem(item);
		}
	

		//fin donde se agregan los items
		
		try {
			
			Preference result =	preference.save();
			
			System.out.println(result.getId());
		    String pathResult =	 result.getSandboxInitPoint();
		
		    LinkPedido linkPedido = new LinkPedido();
		    linkPedido.setUrl(pathResult);
		
		    Gson json = new Gson();
		    PrintWriter out = resp.getWriter();
			resp.setContentType("application/json");
			resp.setCharacterEncoding("utf-8");
			out.print(json.toJson(linkPedido).toString());
			out.flush();
		
		
		} catch (MPException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	
}
