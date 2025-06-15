package com.viajando.controller.usuario;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.JsonObject;

@WebServlet( urlPatterns =  "/LogOut")
public class LogOut extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        JsonObject obj = new JsonObject();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("utf-8");

        try (PrintWriter out = resp.getWriter()) {
            HttpSession jsession = req.getSession(false); 

            if (jsession != null) {
                jsession.invalidate();
            }
            
            setOutResponse("Session cerrada correctamente", resp, 200, "ok");
            
            
        } catch (Exception e) {
        	setOutResponse(e.getMessage(), resp, 400, "error");
        }
        }
    

	private void setOutResponse(String mensaje, HttpServletResponse resp, int statusCode, String status) throws IOException {
        JsonObject obj = new JsonObject();
        obj.addProperty("estatus", status);
        obj.addProperty("mensaje", mensaje);
        resp.setStatus(statusCode);
        PrintWriter out = resp.getWriter();
        out.print(obj.toString());
        out.flush();
		
	}
}