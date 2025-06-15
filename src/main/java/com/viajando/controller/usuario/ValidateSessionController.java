package com.viajando.controller.usuario;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.JsonObject;
import com.viajando.domain.Usuario;

@WebServlet( urlPatterns =  "/existeSession.do")
public class ValidateSessionController extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			validateSession(req, resp);
			setOutResponse("Session activa", resp, 200, "ok");
		} catch (Exception e) {
			setOutResponse("Sin sessioin", resp, 400, "error al obtener la session");
		}
		
		
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			validateSession(req, resp);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void validateSession(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		HttpSession session = req.getSession(true);
		String usuario = (String) session.getAttribute("CURRENT_USER");
		if (usuario == null) {
			throw new IOException ("Session Expirada");
		}
	}
	
	private void setOutResponse(String mensaje,HttpServletResponse resp, int code, String status) throws IOException {
		 PrintWriter out = resp.getWriter();
		 resp.setContentType("application/json");
		 resp.setCharacterEncoding("utf-8");
		 JsonObject obj = new JsonObject();
		 resp.setStatus(code);
		 obj.addProperty("status", status);
		 obj.addProperty("mensaje",mensaje);
		 out.print(obj.toString());
		 out.flush();
	}
	
	
}
