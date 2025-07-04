package com.viajando.controller.paquetes;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalTime;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import org.apache.http.HttpStatus;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.viajando.domain.*;
import com.viajando.parser.Parser;
import com.viajando.parser.ParserTime;
import com.viajando.service.excursion.ExcursionService;
import com.viajando.service.excursion.ExcursionServiceImp;
import com.viajando.service.vuelo.VueloService;
import com.viajando.service.vuelo.VueloServiceImp;
import com.viajando.service.hotel.HotelService;
import com.viajando.service.hotel.HotelServiceImp;
import com.viajando.service.paquete.PaqueteService;
import com.viajando.service.paquete.PaqueteServiceImp;

@WebServlet(urlPatterns = "/carrito.do")
public class CarritoController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	VueloService vueloService = new VueloServiceImp();
	ExcursionService excursionService = new ExcursionServiceImp();
	HotelService hotelService = new HotelServiceImp();
	PaqueteService paqueteService = new PaqueteServiceImp();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String id = req.getParameter("id");
		String tipo = req.getParameter("type");

		HttpSession session = req.getSession(true);
		Carrito carrito = (Carrito) session.getAttribute("carrito");

		if (carrito == null) {
			carrito = new Carrito();
		}

		// Eliminar previamente el servicio del mismo tipo si existe
		carrito.getReservables().removeIf(r -> r.getClass().getSimpleName().equalsIgnoreCase(tipo));

		try {
			switch (tipo) {
				case "VUELO":
					Vuelo vuelo = vueloService.findById(Integer.parseInt(id));
					carrito.getReservables().add(vuelo);
					break;
				case "EXCURSION":
					Excursion excursion = excursionService.findById(Integer.parseInt(id));
					carrito.getReservables().add(excursion);
					break;
				case "HOTEL":
					Hotel hotel = hotelService.findById(Integer.parseInt(id));
					carrito.getReservables().add(hotel);
					break;
				case "PAQUETE":
					Paquete paquete = paqueteService.findById(Integer.parseInt(id));
					carrito.getReservables().add(paquete);
					break;
				default:
					resp.setStatus(HttpStatus.SC_BAD_REQUEST);
					resp.setContentType("application/json");
					resp.setCharacterEncoding("utf-8");
					resp.getWriter().write("{\"error\": true, \"mensaje\": \"Tipo inválido\"}");
					return;
			}

			session.setAttribute("carrito", carrito);

			JsonObject json = new JsonObject();
			json.addProperty("error", false);
			json.addProperty("mensaje", tipo + " agregado/reemplazado correctamente.");
			resp.setContentType("application/json");
			resp.setCharacterEncoding("utf-8");
			resp.setStatus(HttpStatus.SC_OK);
			resp.getWriter().print(json.toString());

		} catch (Exception e) {
			JsonObject json = new JsonObject();
			json.addProperty("error", true);
			json.addProperty("mensaje", "Error al procesar el carrito: " + e.getMessage());
			resp.setStatus(HttpStatus.SC_BAD_REQUEST);
			resp.setContentType("application/json");
			resp.setCharacterEncoding("utf-8");
			resp.getWriter().print(json.toString());
		}
	}
}
