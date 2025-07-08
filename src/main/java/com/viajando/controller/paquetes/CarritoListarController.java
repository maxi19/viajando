package com.viajando.controller.paquetes;

import java.io.IOException;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.viajando.domain.*;

@WebServlet("/carritoListado")
public class CarritoListarController extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        Carrito carrito = (Carrito) session.getAttribute("carrito");

        // ✅ Crear el carrito si no existe
        if (carrito == null) {
            carrito = new Carrito();
            session.setAttribute("carrito", carrito);
        }

        List<Reservable> reservables = carrito.getReservables();
        JsonArray jsonArray = new JsonArray();

        for (Reservable r : reservables) {
            JsonObject json = new JsonObject();
            json.addProperty("id", r.dameId());
            json.addProperty("tipo", r.dameTipo());
            json.addProperty("precio", r.damePrecio());

            if (!(r instanceof Hotel)) {
                json.addProperty("cantidad", r.getCantidadPersonas());
            }

            if (r instanceof Excursion) {
                Excursion excursion = (Excursion) r;
                json.addProperty("nombre", excursion.getNombre());
                json.addProperty("descripcion", excursion.getDescripcion());
                json.addProperty("fecha_inicio", excursion.getFecha_inicio().toString());
                json.addProperty("fecha_fin", excursion.getFecha_fin().toString());
                json.addProperty("imagen", excursion.getImagen());
                json.addProperty("destino", excursion.getDestino().getNombre());
                json.addProperty("estrellas", excursion.getEstrellas());
                json.addProperty("precio", excursion.getPrecio());
            }

            if (r instanceof Vuelo) {
                Vuelo vuelo = (Vuelo) r;
                json.addProperty("nombre", vuelo.getNombre());
                json.addProperty("fecha_inicio", vuelo.getFecha_inicio().toString());
                json.addProperty("fecha_fin", vuelo.getFecha_fin().toString());
                json.addProperty("hora_ida", vuelo.getHora_ida().toString());
                json.addProperty("hora_vuelta", vuelo.getHora_vuelta().toString());
                json.addProperty("imagen", vuelo.getImagen());
                json.addProperty("destino", vuelo.getDestino().getNombre());
                json.addProperty("estrellas", vuelo.getEstrellas());
                json.addProperty("precio", vuelo.getPrecio());
            }

            if (r instanceof Hotel) {
                Hotel hotel = (Hotel) r;
                json.addProperty("nombre", hotel.getNombre());
                json.addProperty("destino", hotel.getDestino().getNombre());
                json.addProperty("imagen", hotel.getImagen());
                json.addProperty("estrellas", hotel.getEstrellas());
                json.addProperty("precio", hotel.getPrecio());
            }

            if (r instanceof Paquete) {
                Paquete paquete = (Paquete) r;
                json.addProperty("nombre", paquete.getNombre());
                json.addProperty("descripcion", paquete.getDescripcion());
                json.addProperty("estrellas", paquete.getEstrellas());
                json.addProperty("personas", paquete.getPersonas());
                json.addProperty("precio", paquete.getPrecio());

                if (paquete.getHotel() != null) {
                    json.addProperty("hotel_id", paquete.getHotel().getId());
                    json.addProperty("hotel_value", paquete.getHotel().getNombre());
                } else {
                    json.addProperty("hotel_id", "-");
                    json.addProperty("hotel_value", "No incluye hotel");
                }

                if (paquete.getVuelo() != null) {
                    json.addProperty("vuelo_id", paquete.getVuelo().getId());
                    json.addProperty("vuelo_value", paquete.getVuelo().getNombre());
                } else {
                    json.addProperty("vuelo_id", "-");
                    json.addProperty("vuelo_value", "No incluye vuelo");
                }

                if (paquete.getExcursion() != null) {
                    json.addProperty("excursion_id", paquete.getExcursion().getId());
                    json.addProperty("excursion_value", paquete.getExcursion().getNombre());
                } else {
                    json.addProperty("excursion_id", "-");
                    json.addProperty("excursion_value", "No incluye excursión");
                }
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
