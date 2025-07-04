package com.viajando.controller.vuelo;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalTime;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.viajando.domain.Destino;
import com.viajando.domain.Vuelo;
import com.viajando.service.vuelo.VueloService;
import com.viajando.service.vuelo.VueloServiceImp;
import com.viajando.dao.DestinoDao;

import com.google.gson.JsonObject;


@MultipartConfig
@WebServlet("/crearVuelo")
public class VueloCrear extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private VueloService vueloService = new VueloServiceImp();
	private DestinoDao DestinoDao = new DestinoDao();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		PrintWriter out = resp.getWriter();
		JsonObject json = new JsonObject();

		try {
			// 1. Obtener parámetros
			String nombre = req.getParameter("nombre");
			int destinoId = Integer.parseInt(req.getParameter("destino_id"));
			LocalDate fechaInicio = LocalDate.parse(req.getParameter("fecha_inicio"));
			LocalDate fechaFin = LocalDate.parse(req.getParameter("fecha_fin"));
			int precio = Integer.parseInt(req.getParameter("precio"));
			double estrellas = Double.parseDouble(req.getParameter("estrellas"));
			LocalTime horaIda = LocalTime.parse(req.getParameter("hora_ida"));
			LocalTime horaVuelta = LocalTime.parse(req.getParameter("hora_vuelta"));
			int idAvion = Integer.parseInt(req.getParameter("id_avion"));

			Part imagenPart = req.getPart("imagen");

			// 2. Crear objeto Vuelo (con imagen temporal)
			Destino destino = DestinoDao.getOne(destinoId);
			Vuelo vuelo = new Vuelo(0, nombre, destino, fechaInicio, fechaFin, precio, estrellas, horaIda, horaVuelta, idAvion, null);

			// 3. Guardar y obtener ID generado
			int idGenerado = vueloService.saveAndReturnId(vuelo);

			// 4. Procesar imagen
			String nombreOriginal = Paths.get(imagenPart.getSubmittedFileName()).getFileName().toString();
			String extension = nombreOriginal.substring(nombreOriginal.lastIndexOf(".") + 1);

			if (!extension.equalsIgnoreCase("jpg") && !extension.equalsIgnoreCase("png") && !extension.equalsIgnoreCase("jpeg")) {
				throw new ServletException("Formato de imagen no permitido.");
			}

			String nombreImagen = "vueloimg" + idGenerado + "." + extension;

			String uploadPath = getServletContext().getRealPath("/images/");
			File uploadDir = new File(uploadPath);
			if (!uploadDir.exists()) uploadDir.mkdir();

			imagenPart.write(uploadPath + File.separator + nombreImagen);

			// 5. Actualizar imagen en DB
			vueloService.updateImage(idGenerado, nombreImagen);

			// 6. Responder
			json.addProperty("estatus", "ok");
			json.addProperty("mensaje", "Vuelo creado exitosamente con ID: " + idGenerado);
			json.addProperty("imagen", nombreImagen);
			out.print(json.toString());
			out.flush();

		} catch (Exception e) {
			e.printStackTrace();
			resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			json.addProperty("estatus", "error");
			json.addProperty("mensaje", "Error al crear vuelo: " + e.getMessage());
			out.print(json.toString());
			out.flush();
		}
	}
	

}
