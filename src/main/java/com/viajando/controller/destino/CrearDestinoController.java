package com.viajando.controller.destino;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;

import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.google.gson.JsonObject;
import com.viajando.domain.Destino;
import com.viajando.service.DestinoService;
import com.viajando.service.DestinoServiceImp;

@WebServlet(urlPatterns = "/crearDestino")
@MultipartConfig // Importante para recibir archivos
public class CrearDestinoController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	DestinoService destinoService = new DestinoServiceImp();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String nombre = req.getParameter("nombre");
		String pais = req.getParameter("pais");
		String precioString = req.getParameter("precio");

		int precioInt = Integer.parseInt(precioString);

		try {
			// Crear el objeto Destino
	        Destino destino = new Destino();
	        destino.setNombre(nombre);
	        destino.setPais(pais);
	        destino.setPrecio(precioInt);

	        // Guardar usando el service
	        destinoService.addDestino(destino);

			resp.setContentType("application/json");
			resp.setCharacterEncoding("utf-8");
			PrintWriter out = resp.getWriter();
			JsonObject obj = new JsonObject();
			obj.addProperty("estatus", "ok");
			obj.addProperty("mensaje", "Destino creado");
			out.print(obj.toString());
			out.flush();

		} catch (Exception e) {
			e.printStackTrace();
			resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			resp.setContentType("application/json");
			resp.setCharacterEncoding("utf-8");
			PrintWriter out = resp.getWriter();
			JsonObject obj = new JsonObject();
			obj.addProperty("estatus", "error");
			obj.addProperty("mensaje", "Error interno: " + e.getMessage());
			out.print(obj.toString());
			out.flush();
			return;
		}
	}
}