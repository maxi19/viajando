package com.viajando.controller.destino;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.viajando.service.DestinoService;
import com.viajando.service.DestinoServiceImp;

@WebServlet(urlPatterns = { "/destinos.do"})
public class DestinoController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DestinoService destinoService = new DestinoServiceImp();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		try {
			Gson json = new Gson();
			PrintWriter out = resp.getWriter();
			resp.setContentType("application/json");
			resp.setCharacterEncoding("utf-8");
			out.print(json.toJson(destinoService.list()).toString());
			out.flush();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
