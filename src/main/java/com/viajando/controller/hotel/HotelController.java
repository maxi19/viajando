package com.viajando.controller.hotel;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.viajando.parser.Parser;
import com.viajando.service.hotel.HotelService;
import com.viajando.service.hotel.HotelServiceImp;


@WebServlet("/hotelController")
public class HotelController extends HttpServlet{

	private static final long serialVersionUID = 1L;
	private HotelService hotelService = new HotelServiceImp();


	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {
			
			Gson gson = new GsonBuilder()
			        .registerTypeAdapter(LocalDate.class, new Parser())
			        .create();
			
			PrintWriter out = resp.getWriter();
			resp.setContentType("application/json");
			resp.setCharacterEncoding("utf-8");
			out.print(gson.toJson(hotelService.list()));
			out.flush();

		} catch (Exception e) {
			
System.out.println(e.getMessage());
		}

	}

}
