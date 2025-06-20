package com.viajando.controller.excursion;

import java.io.IOException;

import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.viajando.service.excursion.ExcursionService;
import com.viajando.service.excursion.ExcursionServiceImp;

import com.google.gson.Gson;

@WebServlet("/excursionController")
public class ExcursionController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private ExcursionService excursionService = new ExcursionServiceImp();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {
			Gson json = new Gson();
			PrintWriter out = resp.getWriter();
			resp.setContentType("application/json");
			resp.setCharacterEncoding("utf-8");
			out.print(json.toJson(excursionService.list()));
			out.flush();

		} catch (Exception e) {

		}

	}

}