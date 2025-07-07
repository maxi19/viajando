package com.viajando.controller.hotel;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.google.gson.JsonObject;
import com.viajando.dao.DestinoDao;
import com.viajando.domain.Destino;
import com.viajando.domain.Hotel;
import com.viajando.service.hotel.HotelService;
import com.viajando.service.hotel.HotelServiceImp;

@MultipartConfig
@WebServlet("/crearHotel")
public class CrearHotelController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private HotelService hotelService = new HotelServiceImp();
	private DestinoDao destinoDao = new DestinoDao();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("application/json");
		resp.setCharacterEncoding("utf-8");
		PrintWriter out = resp.getWriter();
		JsonObject json = new JsonObject();

		try {
			String nombre = req.getParameter("nombre");
			int destinoId = Integer.parseInt(req.getParameter("destino_id"));
			double estrellas = Double.parseDouble(req.getParameter("estrellas"));
			int precio = Integer.parseInt(req.getParameter("precio"));
			int stock = Integer.parseInt(req.getParameter("stock"));
			Part imagenPart = req.getPart("imagen");

			Destino destino = destinoDao.getOne(destinoId);
			Hotel hotel = new Hotel(0, nombre, destino, estrellas, precio, null, stock);

			// Cargar habitaciones
			List<String> tipos = new ArrayList<>();
			List<Integer> capacidades = new ArrayList<>();

			for (int i = 1; i <= stock; i++) {
				String tipo = req.getParameter("habitacion" + i);
				String capacidadStr = req.getParameter("capacidad_habitacion_" + i);
				int capacidad = (capacidadStr != null && !capacidadStr.isEmpty()) ? Integer.parseInt(capacidadStr) : 2;

				tipos.add(tipo != null ? tipo : "Habitación " + i);
				capacidades.add(capacidad);
			}

			hotel.setTiposHabitacion(tipos);
			hotel.setCapacidades(capacidades);

			// Guardar y obtener ID generado
			int idGenerado = hotelService.saveAndReturnId(hotel);

			// Procesar imagen
			String nombreOriginal = Paths.get(imagenPart.getSubmittedFileName()).getFileName().toString();
			String extension = nombreOriginal.substring(nombreOriginal.lastIndexOf('.') + 1);

			if (!extension.matches("(?i)jpg|jpeg|png")) {
				throw new ServletException("Formato de imagen no permitido.");
			}

			String nombreImagen = "hotelimg" + idGenerado + "." + extension;
			String uploadPath = getServletContext().getRealPath("/images/");
			File uploadDir = new File(uploadPath);
			if (!uploadDir.exists()) uploadDir.mkdirs();
			imagenPart.write(uploadPath + File.separator + nombreImagen);

			hotelService.updateImage(idGenerado, nombreImagen);

			// Respuesta
			json.addProperty("estatus", "ok");
			json.addProperty("mensaje", "Hotel creado con ID: " + idGenerado);
			json.addProperty("imagen", nombreImagen);
			out.print(json.toString());
			out.flush();

		} catch (Exception e) {
			e.printStackTrace();
			resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			json.addProperty("estatus", "error");
			json.addProperty("mensaje", "Error al crear hotel: " + e.getMessage());
			out.print(json.toString());
			out.flush();
		}
	}
}
