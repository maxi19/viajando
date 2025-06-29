package com.viajando.controller.excursion;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.viajando.parser.Parser;

import com.viajando.service.excursion.ExcursionService;
import com.viajando.service.excursion.ExcursionServiceImp;

@WebServlet("/listarExcursiones")
public class ExcursionesListas extends HttpServlet {

	private ExcursionService excursionService = new ExcursionServiceImp();

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");

		try {
			Gson gson = new GsonBuilder()
			        .registerTypeAdapter(LocalDate.class, new Parser())
			        .create();			PrintWriter out = resp.getWriter();
			out.print(gson.toJson(excursionService.list()).toString());
			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
