package com.viajando.controller.excursion;
import com.viajando.config.Conexion;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.viajando.service.excursion.ExcursionService;
import com.viajando.service.excursion.ExcursionServiceImp;

@WebServlet(urlPatterns = "/LeerDatosExcursion")
public class LeerDatosExcursion extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	ExcursionService excursionService = new ExcursionServiceImp();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {

			req.setAttribute("excursion", excursionService.list());
			req.getRequestDispatcher("excursionPage.jsp").forward(req, resp);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
