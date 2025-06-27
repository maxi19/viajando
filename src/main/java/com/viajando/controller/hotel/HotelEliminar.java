package com.viajando.controller.hotel;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonObject;

import com.viajando.service.hotel.HotelService;
import com.viajando.service.hotel.HotelServiceImp;

@WebServlet( urlPatterns =  "/HotelEliminar")
public class HotelEliminar extends HttpServlet {

	 HotelService hotelService = new HotelServiceImp();
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");
			
        if (id == null || id.trim().isEmpty()) {
            req.setAttribute("mensajeError", "Error: La descripción no puede estar vacía.");
            req.getRequestDispatcher("/loadHotelFormBaja").forward(req, resp);
            return;     
        }
        
        int idInt = Integer.parseInt(id);
        try {
		  hotelService.delete(idInt);
			
		   PrintWriter out = resp.getWriter();
		   resp.setContentType("application/json");
		   resp.setCharacterEncoding("utf-8");
		   JsonObject obj = new JsonObject();
		   //resp.setStatus(200);
		   obj.addProperty("estatus","ok");
		   obj.addProperty("mensaje","Se elimino correctamente el registro");
		   out.print(obj.toString());
		   out.flush();	   
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        

	}
}

