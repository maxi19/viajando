package com.viajando.controller.paquete;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.viajando.domain.Paquete;
import com.viajando.service.paquete.PaqueteService;
import com.viajando.service.paquete.PaqueteServiceImp;

@WebServlet( urlPatterns =  "/loadPaqueteFormBaja")

public class LoadPaqueteFormBaja extends HttpServlet {

	private PaqueteService paqueteService = new PaqueteServiceImp();
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	RequestDispatcher ds = this.getServletContext().getRequestDispatcher("/paqueteFormBaja.jsp");
		
		try {
			 String id = req.getParameter("id");
			 System.err.println(id);
			 
			 Paquete paquete= paqueteService.findById(Integer.parseInt(id));
			 
			 req.setAttribute("paquete", paquete);
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ds.forward(req, resp);
		
}
}
