package com.viajando.controller.paquete;

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
import com.viajando.service.paquete.PaqueteService;
import com.viajando.service.paquete.PaqueteServiceImp;

@WebServlet(urlPatterns = "/crearPaquete")
@MultipartConfig // Importante para recibir archivos

public class CrearPaqueteController extends HttpServlet  {

	private static final long serialVersionUID = 1L;
	PaqueteService paqueteService = new PaqueteServiceImp();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String nombre = req.getParameter("nombre");
		String descripcion = req.getParameter("descripcion");
		String hotel_idString = req.getParameter("hotel_id");
		String vuelo_idString = req.getParameter("vuelo_id");
		String excursion_idString = req.getParameter("excursion_id");
		String estrellasString = req.getParameter("estrellas");
		String personasString = req.getParameter("personas");
		String precioString = req.getParameter("precio");
		
		int precioInt = Integer.parseInt(precioString);
		int personasInt = Integer.parseInt(personasString);
		double estrellasDouble = Double.parseDouble(estrellasString);
		
		int hotel_idInt = (hotel_idString != null && !hotel_idString.isEmpty()) ? Integer.parseInt(hotel_idString) : 0;
		int vuelo_idInt = (vuelo_idString != null && !vuelo_idString.isEmpty()) ? Integer.parseInt(vuelo_idString) : 0;
		int excursion_idInt = (excursion_idString != null && !excursion_idString.isEmpty()) ? Integer.parseInt(excursion_idString) : 0;
		
		

        // Imagen
        Part imagenPart = req.getPart("imagen");
        
        try {
            // 1. Guardar excursion sin imagen para obtener el ID autogenerado
            int idGenerado = paqueteService.saveAndReturnId(nombre, descripcion,hotel_idInt, vuelo_idInt, excursion_idInt, estrellasDouble, personasInt, precioInt );

            // 2. Definir nombre de imagen: img<ID>.jpg
            String nombreOriginal = Paths.get(imagenPart.getSubmittedFileName()).getFileName().toString();
            String extension = nombreOriginal.substring(nombreOriginal.lastIndexOf(".") + 1);

            if(!extension.equalsIgnoreCase("jpg") && !extension.equalsIgnoreCase("png") && !extension.equalsIgnoreCase("jpeg")) {
                throw new ServletException("Formato de imagen no permitido.");
            }
            
            String paqueteImagen = "paqueteimg" + idGenerado + "." + extension; // Ej: img15.png o img16.jpg

            // 3. Guardar archivo físico
            String uploadPath = getServletContext().getRealPath("/images/");
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) uploadDir.mkdir();
            imagenPart.write(uploadPath + File.separator + paqueteImagen);

            // 4. Actualizar imagen en DB
            paqueteService.updateImage(idGenerado, paqueteImagen);

            // 5. Respuesta JSON
            resp.setContentType("application/json");
            resp.setCharacterEncoding("utf-8");
            PrintWriter out = resp.getWriter();
            JsonObject obj = new JsonObject();
            obj.addProperty("estatus", "ok");
            obj.addProperty("mensaje", "paquete creada con ID: " + idGenerado);
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
