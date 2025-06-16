package com.viajando.controller.excursion;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonObject;
import com.viajando.service.excursion.ExcursionService;
import com.viajando.service.excursion.ExcursionServiceImp;

@WebServlet(urlPatterns = "/crearExcursion")
public class CrearExcursionController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	ExcursionService excursionService = new ExcursionServiceImp();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String nombre = req.getParameter("nombre");
		String descripcion = req.getParameter("descripcion");
		String fecha_inicioString = req.getParameter("fecha_inicio");
		String fecha_finString = req.getParameter("fecha_fin");
		String precioString = req.getParameter("precio");
		String destino = req.getParameter("destino");
		String estrellasString = req.getParameter("estrellas");

		LocalDate fechaInicio = LocalDate.parse(fecha_inicioString);
		LocalDate fechaFin = LocalDate.parse(fecha_finString);
		int precioInt = Integer.parseInt(precioString);
		double estrellasDouble = Double.parseDouble(estrellasString);

		try {

			excursionService.save(nombre, descripcion, fechaInicio, fechaFin, precioInt, destino, estrellasDouble);
			PrintWriter out = resp.getWriter();
			resp.setContentType("application/json");
			resp.setCharacterEncoding("utf-8");
			JsonObject obj = new JsonObject();
			// resp.setStatus(200);
			obj.addProperty("estatus", "ok");
			obj.addProperty("mensaje", "Se creo exitosamente el registro");
			out.print(obj.toString());
			out.flush();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}