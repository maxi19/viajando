package com.viajando.controller.paquete;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalTime;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.viajando.parser.Parser;
import com.viajando.parser.ParserTime;
import com.viajando.service.paquete.PaqueteService;
import com.viajando.service.paquete.PaqueteServiceImp;

@WebServlet("/paqueteController")
public class PaqueteController extends HttpServlet  {

	private static final long serialVersionUID = 1L;
	private PaqueteService paqueteService = new PaqueteServiceImp();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {
			
			Gson gson = new GsonBuilder()
					 .serializeNulls() // 🔹 Incluye los campos que están en null
			        .registerTypeAdapter(LocalDate.class, new Parser())
			        .registerTypeAdapter(LocalTime.class, new ParserTime())
			        .create();
			
			PrintWriter out = resp.getWriter();
			resp.setContentType("application/json");
			resp.setCharacterEncoding("utf-8");
			out.print(gson.toJson(paqueteService.list()));
			out.flush();

		} catch (Exception e) {
			
System.out.println(e.getMessage());
		}

	}

}
