package com.viajando.controller.vuelo;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.google.gson.JsonObject;
import com.viajando.service.vuelo.VueloService;
import com.viajando.service.vuelo.VueloServiceImp;
/**
 * Servlet implementation class VueloEliminar
 */
@WebServlet("/eliminarVuelo")
public class VueloEliminar extends HttpServlet {
	

	 private VueloService vueloService = new VueloServiceImp();

	    @Override
	    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
	            throws ServletException, IOException {

	        String id = req.getParameter("id");

	        if (id == null || id.trim().isEmpty()) {
	            req.setAttribute("mensajeError", "Error: El ID no puede estar vacío.");
	            req.getRequestDispatcher("/VueloFormBaja").forward(req, resp);
	            return;
	        }

	        try {
	            int idInt = Integer.parseInt(id);
	            vueloService.delete(idInt);

	            // JSON de respuesta
	            resp.setContentType("application/json");
	            resp.setCharacterEncoding("utf-8");
	            PrintWriter out = resp.getWriter();
	            JsonObject obj = new JsonObject();
	            obj.addProperty("estatus", "ok");
	            obj.addProperty("mensaje", "Vuelo eliminado correctamente");
	            out.print(obj.toString());
	            out.flush();

	        } catch (NumberFormatException e) {
	            e.printStackTrace();
	            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID inválido");

	        } catch (Exception e) {
	            e.printStackTrace();
	            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error al eliminar el vuelo");
	        }
	    }
}
