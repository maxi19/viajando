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
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.viajando.domain.*;
import com.viajando.parser.Parser;


@WebServlet("/carritoListado")
public class CarritoListarController extends HttpServlet {
	 protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        HttpSession session = request.getSession();
	        Carrito carrito = (Carrito) session.getAttribute("carrito");
	        if (carrito == null) return;

	        List<Reservable> reservables = carrito.getReservables();
	        JsonArray jsonArray = new JsonArray();

	        for (Reservable r : reservables) {
	            JsonObject json = new JsonObject();
	            json.addProperty("id", r.dameId());
	            json.addProperty("cantidad", r.getCantidadPersonas());
	            json.addProperty("tipo", r.dameTipo());
	            json.addProperty("precio", r.damePrecio());

	            if (r instanceof Excursion e) {
	                json.addProperty("nombre", e.getNombre());
	                json.addProperty("descripcion", e.getDescripcion());
	                json.addProperty("fecha_inicio", e.getFecha_inicio().toString());
	                json.addProperty("fecha_fin", e.getFecha_fin().toString());
	                json.addProperty("imagen", e.getImagen());
	                json.addProperty("destino", e.getDestino().getNombre());
	            }

	            if (r instanceof Vuelo v) {
	                json.addProperty("nombre", v.getNombre());
	                json.addProperty("fecha_inicio", v.getFecha_inicio().toString());
	                json.addProperty("fecha_fin", v.getFecha_fin().toString());
	                json.addProperty("hora_ida", v.getHora_ida().toString());
	                json.addProperty("hora_vuelta", v.getHora_vuelta().toString());
	                json.addProperty("imagen", v.getImagen());
	                json.addProperty("destino", v.getDestino().getNombre());
	            }

	            jsonArray.add(json);
	        }

	        response.setContentType("application/json");
	        response.getWriter().print(jsonArray.toString());
	    }

	    @Override
	    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        String action = request.getParameter("action");

	        if ("remove".equalsIgnoreCase(action)) {
	            HttpSession session = request.getSession();
	            Carrito carrito = (Carrito) session.getAttribute("carrito");

	            if (carrito == null) {
	                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "No hay carrito en sesión");
	                return;
	            }

	            try {
	                int id = Integer.parseInt(request.getParameter("id"));
	                String tipo = request.getParameter("tipo");

	                carrito.eliminar(id, tipo);
	                session.setAttribute("carrito", carrito);

	                JsonObject obj = new JsonObject();
	                obj.addProperty("estatus", "ok");
	                obj.addProperty("mensaje", "Elemento eliminado del carrito");
	                response.setContentType("application/json");
	                response.getWriter().print(obj.toString());

	            } catch (Exception e) {
	                e.printStackTrace();
	                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error al eliminar del carrito");
	            }
	        }
	    }
}
