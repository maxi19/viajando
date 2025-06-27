package com.viajando.controller.excursion;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.viajando.service.excursion.ExcursionServiceImp;
import com.google.gson.Gson;
import com.viajando.dao.DestinoDao;
import com.viajando.domain.Destino;
import com.viajando.service.excursion.ExcursionService;


@WebServlet("/destinos")
public class ExcursionControllerList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    private ExcursionService excursionService = new ExcursionServiceImp();
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		DestinoDao dao = new DestinoDao();

		try {
			List<Destino> destinos = dao.list();
			Gson json = new Gson();
			PrintWriter out = resp.getWriter();
			resp.setContentType("application/json");
			resp.setCharacterEncoding("utf-8");
			out.print(json.toJson(destinos));
			out.flush();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	
	
	
}