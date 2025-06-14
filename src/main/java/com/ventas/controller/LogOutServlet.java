package com.ventas.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(urlPatterns = { "/logOut"})
public class LogOutServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession misession= req.getSession(true);	

		if (misession.getAttribute("usuario") != null) {
			misession.removeAttribute("usuario");
		}
		resp.sendRedirect( req.getContextPath()+"/iniciar");
	}
	
	

}
