package com.viajando.controller.vuelo;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;

import com.viajando.service.vuelo.VueloService;
import com.viajando.service.vuelo.VueloServiceImp;

@WebServlet(urlPatterns = "/LeerDatosVuelo")
public class LeerDatosVuelo extends HttpServlet {

	VueloService vueloService = new VueloServiceImp();

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {
			req.setAttribute("vuelos", vueloService.listarVuelos());
			req.getRequestDispatcher("/vuelo/vueloPage.jsp").forward(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
