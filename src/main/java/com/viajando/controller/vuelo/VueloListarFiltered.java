package com.viajando.controller.vuelo;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializer;
import com.viajando.dao.vuelo.VueloDao;
import com.viajando.dao.vuelo.VueloDaoImp;
import com.viajando.domain.Vuelo;

@WebServlet(urlPatterns = "/buscarVuelo"  )
public class VueloListarFiltered extends HttpServlet{

	private VueloDao vueloDao = new VueloDaoImp();
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String fechaIn = req.getParameter("fecha_inicio");
		String fechaOut = req.getParameter("fecha_fin");
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate fechaLocalDate = LocalDate.parse(fechaIn, formatter);
		
		LocalDate fechaOutLocalDate = LocalDate.parse(fechaOut, formatter);
		
		try {
			List<Vuelo> vuelos = vueloDao.findByDate(fechaLocalDate, fechaOutLocalDate);
		     Gson gson = new GsonBuilder()
		                .registerTypeAdapter(LocalDate.class, (JsonSerializer<LocalDate>) (src, typeOfSrc, context) ->
		                    new JsonPrimitive(src.toString()))
		                .registerTypeAdapter(LocalTime.class, (JsonSerializer<LocalTime>) (src, typeOfSrc, context) ->
		                    new JsonPrimitive(src.toString()))
		                .create();
		     
			resp.setContentType("application/json");
			resp.setCharacterEncoding("UTF-8");
			PrintWriter out = resp.getWriter();

			out.print(gson.toJson(vuelos));
			out.flush();
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


}
