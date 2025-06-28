package com.viajando.controller.paquete;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.viajando.service.paquete.PaqueteService;
import com.viajando.service.paquete.PaqueteServiceImp;

@WebServlet(urlPatterns = "/LeerDatosPaquete")

public class LeerDatosPaquete extends HttpServlet {

	private static final long serialVersionUID = 1L;
	PaqueteService paqueteService = new PaqueteServiceImp();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {

			req.setAttribute("paquete", paqueteService.list());
			req.getRequestDispatcher("dashboard.jsp").forward(req, resp);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
