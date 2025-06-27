package com.viajando.controller.vuelo;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.sql.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.viajando.domain.Vuelo;
import com.viajando.service.vuelo.VueloService;
import com.viajando.service.vuelo.VueloServiceImp;
import com.viajando.parser.Parser;
import com.viajando.parser.ParserTime;

/**
 * Servlet implementation class VueloController
 */
@WebServlet(urlPatterns = "/vueloController"  )
public class VueloController extends HttpServlet {
	
	 private static final long serialVersionUID = 1L;

	    private VueloService vueloService = new VueloServiceImp();

	    @Override
	    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {

			try {
				Gson gson = new GsonBuilder()
					.registerTypeAdapter(LocalDate.class, new Parser())
					.registerTypeAdapter(LocalTime.class, new ParserTime())
					.create();

				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
				PrintWriter out = response.getWriter();

				out.print(gson.toJson(vueloService.list()));
				out.flush();
			} catch (Exception e) {
				e.printStackTrace();
				response.sendError(500, "Error al obtener vuelos");
			}
		}

}
