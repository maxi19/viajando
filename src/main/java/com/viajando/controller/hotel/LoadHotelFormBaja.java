package com.vijando.controller.hotel;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.viajando.domain.Hotel;
import com.viajando.service.hotel.HotelService;
import com.viajando.service.hotel.HotelServiceImp;

@WebServlet( urlPatterns =  "/loadHotelFormBaja")
public class LoadHotelFormBaja extends HttpServlet {

	private HotelService hotelService = new HotelServiceImp();
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		RequestDispatcher ds = this.getServletContext().getRequestDispatcher("/excursionFormBaja.jsp");
			
			try {
				 String id = req.getParameter("id");
				 System.err.println(id);
				 
				 Hotel hotel = hotelService.findById(Integer.parseInt(id));
				 
				 req.setAttribute("hotel", hotel);
			
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			ds.forward(req, resp);
			
	}
}