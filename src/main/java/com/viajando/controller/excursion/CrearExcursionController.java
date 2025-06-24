package com.viajando.controller.excursion;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.time.LocalDate;


import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.google.gson.JsonObject;
import com.viajando.service.excursion.ExcursionService;
import com.viajando.service.excursion.ExcursionServiceImp;

@WebServlet(urlPatterns = "/crearExcursion")
@MultipartConfig // Importante para recibir archivos
public class CrearExcursionController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	ExcursionService excursionService = new ExcursionServiceImp();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String nombre = req.getParameter("nombre");
		String descripcion = req.getParameter("descripcion");
		String fecha_inicioString = req.getParameter("fecha_inicio");
		String fecha_finString = req.getParameter("fecha_fin");
		String precioString = req.getParameter("precio");
		String destino = req.getParameter("destino");
		String estrellasString = req.getParameter("estrellas");

		LocalDate fechaInicio = LocalDate.parse(fecha_inicioString);
		LocalDate fechaFin = LocalDate.parse(fecha_finString);
		int precioInt = Integer.parseInt(precioString);
		double estrellasDouble = Double.parseDouble(estrellasString);

        // Imagen
        Part imagenPart = req.getPart("imagen");
        
        try {
            // 1. Guardar excursion sin imagen para obtener el ID autogenerado
            int idGenerado = excursionService.saveAndReturnId(nombre, descripcion, fechaInicio, fechaFin, precioInt, destino, estrellasDouble);
            
            // 2. Definir nombre de imagen: img<ID>.jpg
            String nombreOriginal = Paths.get(imagenPart.getSubmittedFileName()).getFileName().toString();
            String extension = nombreOriginal.substring(nombreOriginal.lastIndexOf(".") + 1);

            if(!extension.equalsIgnoreCase("jpg") && !extension.equalsIgnoreCase("png") && !extension.equalsIgnoreCase("jpeg")) {
                throw new ServletException("Formato de imagen no permitido.");
            }
            
            String nombreImagen = "excursionimg" + idGenerado + "." + extension; // Ej: img15.png o img16.jpg

            // 3. Guardar archivo físico
            String uploadPath = getServletContext().getRealPath("/images/");
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) uploadDir.mkdir();
            imagenPart.write(uploadPath + File.separator + nombreImagen);

            // 4. Actualizar imagen en DB
            excursionService.updateImage(idGenerado, nombreImagen);

            // 5. Respuesta JSON
            resp.setContentType("application/json");
            resp.setCharacterEncoding("utf-8");
            PrintWriter out = resp.getWriter();
            JsonObject obj = new JsonObject();
            obj.addProperty("estatus", "ok");
            obj.addProperty("mensaje", "Excursion creada con ID: " + idGenerado);
            out.print(obj.toString());
            out.flush();

        } catch (Exception e) {
            e.printStackTrace();
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.setContentType("application/json");
            resp.setCharacterEncoding("utf-8");
            PrintWriter out = resp.getWriter();
            JsonObject obj = new JsonObject();
            obj.addProperty("estatus", "error");
            obj.addProperty("mensaje", "Error interno: " + e.getMessage());
            out.print(obj.toString());
            out.flush();
            return;
        }
	}
}