package com.viajando.controller.excursion;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.viajando.domain.Excursion;
import com.viajando.service.excursion.ExcursionService;
import com.viajando.service.excursion.ExcursionServiceImp;

@WebServlet( urlPatterns =  "/loadExcursionFormBaja")
public class LoadExcursionFormBaja extends HttpServlet{
	
		private ExcursionService excursionService = new ExcursionServiceImp();
		
		protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		RequestDispatcher ds = this.getServletContext().getRequestDispatcher("/excursionFormBaja.jsp");
			
			try {
				 String id = req.getParameter("id");
				 System.err.println(id);
				 
				 Excursion excursion = excursionService.findById(Integer.parseInt(id));
				 
				 req.setAttribute("excursion", excursion);
			
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			ds.forward(req, resp);
			
	}
}