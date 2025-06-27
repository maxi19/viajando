package com.viajando.controller.excursion;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.viajando.service.excursion.ExcursionServiceImp;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.viajando.parser.Parser;
import com.viajando.service.excursion.ExcursionService;


@WebServlet("/destinos")
public class ExcursionControllerList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    private ExcursionService excursionService = new ExcursionServiceImp();
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			Gson gson = new GsonBuilder()
			        .registerTypeAdapter(LocalDate.class, new Parser())
			        .create();			PrintWriter out = resp.getWriter();
			resp.setContentType("application/json");
			resp.setCharacterEncoding("utf-8");
			out.print(gson.toJson(excursionService.list()).toString());
			out.flush();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	
	
	
}