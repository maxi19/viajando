package com.viajando.controller.usuario;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.viajando.service.LoginService;
import com.viajando.service.LoginServicelmp;



@WebServlet( urlPatterns =  "/login.do")
public class IngresarUsuario extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	LoginService usuarioservice = new LoginServicelmp();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String usuario = req.getParameter("username");
		String password = req.getParameter("password");		
		
		try {
			
			if (usuario.length()== 0 || usuario == null)
				throw new Exception("usuario vacio");
			
			if (password.length()== 0 || password == null)
				throw new Exception("password vacio");
					
			usuarioservice.consultarUsuario(usuario);
			
			usuarioservice.consultarUsuarioYpassword(usuario, password);
			
			HttpSession jsession = req.getSession(true);
			jsession.setAttribute("CURRENT_USER", usuario);
			
			try {
				setOutResponse("Se logueo corectamente", resp, 200, "ok");
			} catch (Exception e) {
				setOutResponse("Error en login", resp, 400, "error en usuario o password");
			}
		
		} catch (Exception e) {
			setOutResponse(e.getMessage(), resp, 400, "error");
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
