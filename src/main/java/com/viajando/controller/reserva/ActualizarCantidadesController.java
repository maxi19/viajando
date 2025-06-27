package com.viajando.controller.reserva;

import java.io.BufferedReader;
import java.io.IOException;
import java.time.LocalDate;
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
import com.viajando.domain.Reservable;
import com.viajando.parser.Parser;



@WebServlet("/actualizarCantidades")
public class ActualizarCantidadesController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		Carrito carrito = (Carrito) session.getAttribute("carrito");
		if (carrito == null) return;

		// Parsear JSON recibido
		StringBuilder sb = new StringBuilder();
		String line;
		BufferedReader reader = req.getReader();
		while ((line = reader.readLine()) != null) {
			sb.append(line);
		}

		Gson gson = new GsonBuilder()
		        .registerTypeAdapter(LocalDate.class, new Parser())
		        .create();		List<Map<String, Object>> lista = gson.fromJson(sb.toString(), List.class);

		for (Map<String, Object> item : lista) {
			Double idDouble = (Double) item.get("id");
			Integer id = idDouble.intValue();
			Double cantidadDouble = (Double) item.get("cantidad");
			Integer cantidad = cantidadDouble.intValue();

			for (Object obj : carrito.getReservables()) {
				if (obj instanceof Excursion e && e.getId() == id) {
					e.setCantidadPersonas(cantidad);
				}
			}
		}
	}
}
