package com.viajando.controller.vuelo;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.viajando.service.vuelo.VueloService;
import com.viajando.service.vuelo.VueloServiceImp;

/**
 * Servlet implementation class LeerDatosVuelo
 */
@WebServlet("/LeerDatosVuelos")
public class LeerDatosVuelo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	VueloService vueloService = new VueloServiceImp();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {

			req.setAttribute("vuelos", vueloService.list());
			req.getRequestDispatcher("dashboard.jsp").forward(req, resp);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
