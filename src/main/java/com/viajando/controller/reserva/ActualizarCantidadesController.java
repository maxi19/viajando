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

	    StringBuilder sb = new StringBuilder();
	    String line;
	    BufferedReader reader = req.getReader();
	    while ((line = reader.readLine()) != null) {
	        sb.append(line);
	    }

	    Gson gson = new GsonBuilder()
	            .registerTypeAdapter(LocalDate.class, new Parser())
	            .create();

	    List<Map<String, Object>> lista = gson.fromJson(sb.toString(), List.class);

	    for (Map<String, Object> item : lista) {
	        int id = 0;
	        Object idObj = item.get("id");

	        if (idObj instanceof Number) {
	            id = ((Number) idObj).intValue();
	        } else if (idObj instanceof String) {
	            try {
	                if (!((String) idObj).equalsIgnoreCase("undefined")) {
	                    id = Integer.parseInt((String) idObj);
	                }
	            } catch (NumberFormatException e) {
	                continue;
	            }
	        }

	        int cantidad = 1;
	        Object cantidadObj = item.get("cantidad");

	        if (cantidadObj instanceof Number) {
	            cantidad = ((Number) cantidadObj).intValue();
	        } else if (cantidadObj instanceof String) {
	            try {
	                if (!((String) cantidadObj).equalsIgnoreCase("undefined")) {
	                    cantidad = Integer.parseInt((String) cantidadObj);
	                }
	            } catch (NumberFormatException e) {
	                // mantener valor por defecto
	            }
	        }

	        for (Object obj : carrito.getReservables()) {
	            if (obj instanceof Reservable r && r.dameId() == id) {
	                r.setCantidadPersonas(cantidad);
	            }
	        }
	    }
	}
}