package com.ventas.controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mercadopago.resources.Payment;

@WebServlet(urlPatterns = { "/success"})
public class SellerSuccessController extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/success.jsp");
		
	
	
	
		dispatcher.forward(req, resp);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/success.jsp");
		
		dispatcher.forward(req, resp);
		
		Payment payment = new Payment();
		
		
		
		
	}



}
