package com.viajando.controller.paquetes;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.viajando.domain.*;
import com.viajando.parser.Parser;

@WebServlet("/carritoListado")
public class CarritoListarController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession(true);
		Carrito carrito = (Carrito) session.getAttribute("carrito");
		if (carrito == null) {
			carrito = new Carrito();
		}

		List<Map<String, Object>> listaFinal = new ArrayList<>();

		for (Object obj : carrito.getReservables()) {
			Map<String, Object> map = new LinkedHashMap<>();

			if (obj instanceof Vuelo) {
				Vuelo v = (Vuelo) obj;
				map.put("tipo", "Vuelo");
				map.put("nombre", v.getNombre());
				map.put("fecha_inicio", v.getFecha_inicio());
				map.put("fecha_inicio", v.getFecha_fin());
				map.put("precio", v.getPrecio());
				map.put("imagen", v.getImagen());
				map.put("estrellas", v.getEstrellas());

			} else if (obj instanceof Excursion) {
				Excursion e = (Excursion) obj;
				map.put("tipo", "Excursion");
				map.put("nombre", e.getNombre());
				map.put("descripcion", e.getDescripcion());
				map.put("fecha_inicio", e.getFecha_inicio());
				map.put("fecha_fin", e.getFecha_fin());
				map.put("precio", e.getPrecio());
				map.put("imagen", e.getImagen());
				map.put("estrellas", e.getEstrellas());
			}
			// Podés seguir agregando más tipos: Hotel, Paquete, etc.
			listaFinal.add(map);
		}

		Gson gson = new GsonBuilder()
				.registerTypeAdapter(LocalDate.class, new Parser())
				.create();

		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		PrintWriter out = resp.getWriter();
		out.print(gson.toJson(listaFinal));
		out.flush();
	}
}