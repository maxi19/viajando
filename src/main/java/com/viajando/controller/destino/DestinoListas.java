package com.viajando.controller.destino;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.viajando.parser.Parser;
import com.viajando.domain.Destino;
import com.viajando.service.DestinoService;
import com.viajando.service.DestinoServiceImp;

@WebServlet("/listarDestinos")
public class DestinoListas extends HttpServlet {

	 private DestinoService destinoService = new DestinoServiceImp();

	    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
	        resp.setContentType("application/json");
	        resp.setCharacterEncoding("UTF-8");

	        try {
	        	Gson gson = new GsonBuilder()
				        .registerTypeAdapter(LocalDate.class, new Parser())
				        .create();			PrintWriter out = resp.getWriter();
				        resp.setContentType("application/json");
						resp.setCharacterEncoding("utf-8");
						out.print(gson.toJson(destinoService.list()).toString());
						out.flush();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					
				}
}
