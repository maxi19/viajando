package com.viajando.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet( urlPatterns =  "/redirect")
public class LoadForm extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static String PAGE_HOME ="dashboard.jsp";

	private static String BEBIDA_FORM ="bebida/BebidaForm.jsp";

	private static String POSTRE_FORM ="postre/postreForm.jsp";

	private static String MENU_FORM ="menu/menuForm.jsp";

	private static String LOGIN_FORM ="usuario/login.jsp";
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session =   req.getSession(true);	

		if (req.getParameter("method").equals("home") && session.getAttribute("CURRENT_USER") != null) {
			req.getRequestDispatcher(PAGE_HOME).forward(req, resp);
		}else {
			req.getRequestDispatcher(LOGIN_FORM).forward(req, resp);
		}
	}
	
}
