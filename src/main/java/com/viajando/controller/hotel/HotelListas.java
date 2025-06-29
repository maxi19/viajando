package com.viajando.controller.hotel;

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

import com.viajando.service.hotel.HotelService;
import com.viajando.service.hotel.HotelServiceImp;

@WebServlet("/listarHoteles")
public class HotelListas extends HttpServlet {

	private HotelService hotelService = new HotelServiceImp();
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");

		try {
			Gson gson = new GsonBuilder()
			        .registerTypeAdapter(LocalDate.class, new Parser())
			        .create();			PrintWriter out = resp.getWriter();
			out.print(gson.toJson(hotelService.list()).toString());
			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
