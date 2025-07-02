package com.viajando.controller.mercadopago;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mercadopago.MercadoPago;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.MerchantOrder;
import com.mercadopago.resources.Payment;
@WebServlet(urlPatterns = "/notificar")
public class NotificacionController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		MerchantOrder merchantOrder = new MerchantOrder();
		
		try {
			merchantOrder.findById("");
		
		
		
		} catch (MPException e) {
			
			e.printStackTrace();
		}
		
	}

}
