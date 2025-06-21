package com.viajando.controller.vuelo;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.connector.Request;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;

import com.viajando.domain.Vuelo;
import com.viajando.service.vuelo.VueloService;
import com.viajando.service.vuelo.VueloServiceImp;

@WebServlet(urlPatterns = "loadVueloForm")
public class LoadVueloForm extends HttpServlet {

	private VueloService vueloService = new VueloServiceImp();

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		RequestDispatcher ds = this.getServletContext().getRequestDispatcher("/VueloForm.jsp");

		try {
			String id = req.getParameter("id");

			Vuelo vuelo = vueloService.findById(Integer.parseInt(id));

			req.setAttribute("vuelo", vuelo);
		} catch (Exception e) {

			e.printStackTrace();

		}
		ds.forward(req, resp);
	}
}