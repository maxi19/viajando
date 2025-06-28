package com.viajando.controller.paquetes;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.viajando.domain.Carrito;

@WebServlet("/carritoEliminar")
public class CarritoEliminarController extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String tipo = request.getParameter("tipo");

		HttpSession session = request.getSession();
		Carrito carrito = (Carrito) session.getAttribute("carrito");

		if (carrito != null) {
			carrito.eliminar(id, tipo);
		}

		response.setStatus(HttpServletResponse.SC_OK);
	}
}