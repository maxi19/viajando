package com.viajando.controller.mercadopago;

import java.io.IOException;
import java.io.InputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mercadopago.MercadoPago;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.Preference;
import com.mercadopago.resources.datastructures.preference.BackUrls;
import com.mercadopago.resources.datastructures.preference.Item;

import com.viajando.domain.Carrito;
import com.viajando.domain.Excursion;
import com.viajando.domain.Hotel;
import com.viajando.domain.Vuelo;

@WebServlet(urlPatterns = "/generarCupon")
public class GenerarCuponPagoController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession(false);
		if (session == null) {
			resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			resp.getWriter().write("{\"error\":\"No hay sesión\"}");
			return;
		}

		Carrito carrito = (Carrito) session.getAttribute("carrito");
		if (carrito == null || carrito.getReservables().isEmpty()) {
			resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			resp.getWriter().write("{\"error\":\"Carrito vacío\"}");
			return;
		}

		Properties properties = getProperties();
		String token = properties.getProperty("mercadopago.private.accesstoken");

		try {
			MercadoPago.SDK.setAccessToken(token);

			String notificationUrl = "http://localhost:8080/generic"; // Cambiar por tu dominio real

			Preference preference = new Preference();
			preference.setBackUrls(
				new BackUrls()
					.setSuccess(notificationUrl)
					.setPending(notificationUrl)
					.setFailure(notificationUrl)
			);

			ArrayList<Item> itemsMP = new ArrayList<>();

			for (Object obj : carrito.getReservables()) {
				Item item = new Item();

				if (obj instanceof Excursion) {
					Excursion excursion = (Excursion) obj;
					item.setTitle("Excursión: " + excursion.getNombre());
					item.setQuantity(excursion.getCantidadPersonas());
					item.setUnitPrice((float) excursion.getPrecio());

				} else if (obj instanceof Vuelo) {
					Vuelo vuelo = (Vuelo) obj;
					item.setTitle("Vuelo: " + vuelo.getNombre());
					item.setQuantity(vuelo.getCantidadPersonas());
					item.setUnitPrice((float) vuelo.getPrecio());

				} else if (obj instanceof Hotel) {
					Hotel hotel = (Hotel) obj;
					item.setTitle("Hotel: " + hotel.getNombre());
					item.setQuantity(hotel.getCantidadPersonas());
					item.setUnitPrice((float) hotel.getPrecio());

				} else {
					continue; // por si hay objetos no reconocidos
				}

				itemsMP.add(item);
			}

			preference.setItems(itemsMP);

			preference.save();

			// Devolver la URL de pago
			resp.setContentType("application/json");
			resp.setCharacterEncoding("UTF-8");
			resp.getWriter().write("{\"init_point\":\"" + preference.getInitPoint() + "\"}");

		} catch (MPException e) {
			e.printStackTrace();
			resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			resp.getWriter().write("{\"error\":\"Error al crear preferencia\"}");
		}
	}

	private Properties getProperties() throws IOException {
		InputStream inputStream;
		Properties prop = new Properties();
		String propFileName = "environment.properties";

		inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
		if (inputStream != null) {
			prop.load(inputStream);
		} else {
			throw new FileNotFoundException("Archivo '" + propFileName + "' no encontrado en classpath");
		}

		inputStream.close();
		return prop;
	}
}
