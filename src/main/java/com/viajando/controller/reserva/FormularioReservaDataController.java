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
import com.viajando.domain.Paquete;
import com.viajando.domain.Reservable;
import com.viajando.parser.Parser;

@WebServlet("/formularioReservaData")
public class FormularioReservaDataController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Obtener sesión y carrito
        HttpSession session = req.getSession();
        Carrito carrito = (Carrito) session.getAttribute("carrito");

        // Lista de personas para devolver como JSON
        List<Map<String, Object>> personas = new ArrayList<>();

        if (carrito != null && carrito.getReservables() != null) {
        	for (Object obj : carrito.getReservables()) {
        		if (obj instanceof Reservable) {
        			Reservable item = (Reservable) obj;

        			Map<String, Object> persona = new HashMap<>();
        			persona.put("tipo", item.dameTipo().toLowerCase());
        			persona.put("servicio_id", item.dameId());
        			persona.put("nombre_servicio", item.dameTipo());
        			persona.put("precio", item.damePrecio());

        			// 👇 Aquí controlamos cantidad correctamente
        			if (item instanceof Paquete) {
        				Paquete paquete = (Paquete) item;
        				persona.put("cantidad", paquete.getPersonas()); // ✅ cantidad real del paquete

        				if (paquete.tieneVueloEnPaquete()) {
        					persona.put("vuelo_id", paquete.getVueloId());
        				}
        			} else {
        				persona.put("cantidad", item.getCantidadPersonas()); // vuelo, excursión, etc.
        			}

        			personas.add(persona); // ✅ Solo uno por servicio
        		}
        	}
            
        }

        // Serializar respuesta JSON
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new Parser())
                .create();

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        gson.toJson(personas, resp.getWriter());
    }
}
