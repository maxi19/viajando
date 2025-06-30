package com.viajando.controller.reserva;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.viajando.domain.Carrito;
import com.viajando.domain.Excursion;
import com.viajando.domain.Hotel;
import com.viajando.domain.Vuelo;
import com.viajando.parser.Parser;

@WebServlet("/formularioReservaData")
public class FormularioReservaDataController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		Carrito carrito = (Carrito) session.getAttribute("carrito");
		List<Map<String, Object>> personas = new ArrayList<>();

		if (carrito != null) {
			for (Object obj : carrito.getReservables()) {
				if (obj instanceof Excursion) {
					Excursion excursion = (Excursion) obj;

					int cantidad = excursion.getCantidadPersonas();
					for (int i = 0; i < cantidad; i++) {
						Map<String, Object> persona = new HashMap<>();
						persona.put("tipo", "excursion");
						persona.put("servicio_id", excursion.getId());
						persona.put("nombre_servicio", excursion.getNombre());
						personas.add(persona);
					}
				} else if (obj instanceof Vuelo) {
					Vuelo vuelo = (Vuelo) obj;

					int cantidad = vuelo.getCantidadPersonas();
					for (int i = 0; i < cantidad; i++) {
						Map<String, Object> persona = new HashMap<>();
						persona.put("tipo", "vuelo");
						persona.put("servicio_id", vuelo.getId());
						persona.put("nombre_servicio", vuelo.getNombre());
						personas.add(persona);
					}
				} else if (obj instanceof Hotel) {
					 Hotel hotel = (Hotel) obj;
					int cantidad = hotel.getCantidadPersonas();
					for (int i = 0; i < cantidad; i++) {
						Map<String, Object> persona = new HashMap<>();
						persona.put("tipo", "hotel");
						persona.put("servicio_id", hotel.getId());
						persona.put("nombre_servicio", hotel.getNombre());
						personas.add(persona);
					}
					
					
				}
				// A futuro: hoteles, paquetes...
			}
		}

		Gson gson = new GsonBuilder()
		        .registerTypeAdapter(LocalDate.class, new Parser())
		        .create();
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		gson.toJson(personas, resp.getWriter());
	}
}