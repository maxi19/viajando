package com.viajando.controller.vuelo;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.viajando.domain.Destino;
import com.viajando.service.vuelo.VueloService;
import com.viajando.service.vuelo.VueloServiceImp;

@WebServlet(urlPatterns = "/crearVuelo")
public class CrearVueloController extends HttpServlet {

	VueloService vueloService = new VueloServiceImp();

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String destino = req.getParameter("destino");
		String idaString = req.getParameter("ida");
		String vueltaString = req.getParameter("vuelta");
		String precioString = req.getParameter("precio");
		String estrellasString = req.getParameter("estrellas");
		String hora_ida = req.getParameter("hora_ida");
		String hora_vuelta = req.getParameter("hora_vuelta");
		
		
		//convertir los valores
		LocalDate idaDate = LocalDate.parse(idaString);
		LocalDate vueltaDate = LocalDate.parse(vueltaString);
		int precioInt = Integer.parseInt(precioString);
		int estrellasDouble = Integer.parseInt(estrellasString);
		LocalTime horaIda = LocalTime.parse(hora_ida);
		LocalTime horaVuelta = LocalTime.parse(hora_vuelta);
		
		
	}
}
