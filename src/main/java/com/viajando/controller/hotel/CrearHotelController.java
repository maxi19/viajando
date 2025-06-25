package com.vijando.controller.hotel;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.google.gson.JsonObject;
import com.viajando.service.hotel.HotelService;
import com.viajando.service.hotel.HotelServiceImp;

@WebServlet(urlPatterns = "/crearHotel")
@MultipartConfig
public class CrearHotelController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    HotelService hotelService = new HotelServiceImp();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String nombre = req.getParameter("nombre");
        String destinoValue = req.getParameter("destino_value");
        String estrellasString = req.getParameter("estrellas");
        String precioString = req.getParameter("precio");
        String destinoIdString = req.getParameter("destino_id");

        int destino_id = Integer.parseInt(destinoIdString);
        int precio = Integer.parseInt(precioString);
        double estrellas = Double.parseDouble(estrellasString);

        Part imagenPart = req.getPart("imagen");

        try {
            // Guardar hotel sin imagen primero para obtener ID
        	
        	 int idGenerado = hotelService.saveAndReturnId(nombre, destino_id, destinoValue, estrellas, precio);
            // Validar imagen y generar nombre de archivo
            String nombreOriginal = Paths.get(imagenPart.getSubmittedFileName()).getFileName().toString();
            String extension = nombreOriginal.substring(nombreOriginal.lastIndexOf('.') + 1);

            if (!extension.equalsIgnoreCase("jpg") && 
                !extension.equalsIgnoreCase("jpeg") && 
                !extension.equalsIgnoreCase("png")) {
                throw new ServletException("Formato de imagen no permitido.");
            }

            String nombreImagen = "hotelimg" + idGenerado + "." + extension;

            // Guardar archivo físico
            String uploadPath = getServletContext().getRealPath("/images/");
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) uploadDir.mkdirs();
            imagenPart.write(uploadPath + File.separator + nombreImagen);

            // Actualizar imagen en la base de datos
            hotelService.updateImage(idGenerado, nombreImagen);

            // Respuesta JSON
            resp.setContentType("application/json");
            resp.setCharacterEncoding("utf-8");
            PrintWriter out = resp.getWriter();
            JsonObject obj = new JsonObject();
            obj.addProperty("estatus", "ok");
            obj.addProperty("mensaje", "Hotel creado con ID: " + idGenerado);
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
        }
    }
}
