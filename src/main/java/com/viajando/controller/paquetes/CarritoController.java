package com.viajando.controller.paquetes;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalTime;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.http.HttpStatus;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.viajando.domain.Carrito;
import com.viajando.domain.Excursion;
import com.viajando.domain.Vuelo;
import com.viajando.parser.Parser;
import com.viajando.parser.ParserTime;
import com.viajando.service.excursion.ExcursionService;
import com.viajando.service.excursion.ExcursionServiceImp;
import com.viajando.service.vuelo.VueloService;
import com.viajando.service.vuelo.VueloServiceImp;

@WebServlet( urlPatterns =  "/carrito.do")
public class CarritoController extends HttpServlet {

	VueloService vueloService = new VueloServiceImp();
	ExcursionService excursionService = new ExcursionServiceImp();

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		
	String id =  req.getParameter("id");
	String tipo= req.getParameter("type");
	HttpSession	session = req.getSession(true);
	Carrito carrito = (Carrito)session.getAttribute("carrito");
	
	if (carrito == null) {
		carrito = new Carrito();
	}
	
	try {
		if (tipo.equals("VUELO")) {
			  Vuelo vuelo = this.vueloService.findById(Integer.parseInt(id));
			  carrito.getReservables().add(vuelo);
			  session.setAttribute("carrito", carrito);
			} else if (tipo.equals("EXCURSION")) {
			  Excursion excursion = this.excursionService.findById(Integer.parseInt(id));
			  carrito.getReservables().add(excursion);
			  session.setAttribute("carrito", carrito);
			}
		
		Gson gson = new GsonBuilder()
				.registerTypeAdapter(LocalDate.class, new Parser())
				.registerTypeAdapter(LocalTime.class, new ParserTime())
				.create();
		
		PrintWriter out = resp.getWriter();
		resp.setContentType("application/json");
		resp.setCharacterEncoding("utf-8");
		resp.setStatus(HttpStatus.SC_OK);
		out.print(gson.toJson(carrito));
		out.flush();
		
		} catch (NumberFormatException e) {
			Gson gson = new GsonBuilder()
			        .registerTypeAdapter(LocalDate.class, new Parser())
			        .create();			PrintWriter out = resp.getWriter();
			resp.setContentType("application/json");
			resp.setCharacterEncoding("utf-8");
			resp.setStatus(HttpStatus.SC_BAD_REQUEST);
			out.print(gson.toJson(carrito));
			out.flush();
		} catch (Exception e) {
			Gson gson = new GsonBuilder()
			        .registerTypeAdapter(LocalDate.class, new Parser())
			        .create();			PrintWriter out = resp.getWriter();
			resp.setContentType("application/json");
			resp.setCharacterEncoding("utf-8");
			resp.setStatus(HttpStatus.SC_BAD_REQUEST);
			out.print(gson.toJson(carrito));
			out.flush();
		}
	

	}	
	
	
	
}
