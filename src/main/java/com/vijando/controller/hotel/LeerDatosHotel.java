package com.vijando.controller.hotel;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.viajando.service.hotel.HotelService;
import com.viajando.service.hotel.HotelServiceImp;

@WebServlet(urlPatterns = "/LeerDatosHotel")
public class LeerDatosHotel {

	private static final long serialVersionUID = 1L;
	HotelService hotelService = new HotelServiceImp();

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {

			req.setAttribute("hotel", hotelService.list());
			req.getRequestDispatcher("hotel/hotelPage.jsp").forward(req, resp);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}

