
package com.viajando.controller.excursion;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonObject;
import com.viajando.config.Conexion;
import com.viajando.service.excursion.ExcursionService;
import com.viajando.service.excursion.ExcursionServiceImp;

@WebServlet( urlPatterns =  "/ExcursionEliminar")
public class ExcursionEliminar extends HttpServlet {
	
	ExcursionService excursionService = new ExcursionServiceImp();
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");
			
        if (id == null || id.trim().isEmpty()) {
            req.setAttribute("mensajeError", "Error: La descripción no puede estar vacía.");
            req.getRequestDispatcher("/loadExcursionFormBaja").forward(req, resp);
            return;     
        }
        
        int idInt = Integer.parseInt(id);
        try {
		  excursionService.delete(idInt);
			
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
