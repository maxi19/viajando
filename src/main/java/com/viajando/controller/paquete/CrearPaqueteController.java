package com.viajando.controller.paquete;

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
import com.viajando.service.paquete.PaqueteService;
import com.viajando.service.paquete.PaqueteServiceImp;

@WebServlet(urlPatterns = { "/crearPaqueteCompleto", "/crearPaqueteSimple" })
@MultipartConfig
public class CrearPaqueteController extends HttpServlet {

    private PaqueteService paqueteService = new PaqueteServiceImp();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String ruta = req.getServletPath();

        try {
            if (ruta.equals("/crearPaqueteCompleto")) {
                crearPaqueteCompleto(req, resp);
            } else if (ruta.equals("/crearPaqueteSimple")) {
                crearPaqueteSimple(req, resp);
            } else {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Ruta no encontrada");
            }
        } catch (Exception e) {
            responderError(resp, e);
        }
    }

    private void crearPaqueteCompleto(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String nombre = req.getParameter("nombre");
        String descripcion = req.getParameter("descripcion");
        String estrellasString = req.getParameter("estrellas");
        String personasString = req.getParameter("personas");
        String precioString = req.getParameter("precio");

        String hotel_idString = req.getParameter("hotel_id");
        String vuelo_idString = req.getParameter("vuelo_id");
        String excursion_idString = req.getParameter("excursion_id");

        int hotel_idInt = Integer.parseInt(hotel_idString);
        int vuelo_idInt = Integer.parseInt(vuelo_idString);
        int excursion_idInt = Integer.parseInt(excursion_idString);
        double estrellasDouble = Double.parseDouble(estrellasString);
        int personasInt = Integer.parseInt(personasString);
        int precioInt = Integer.parseInt(precioString);

        int idGenerado = paqueteService.saveAndReturnId(nombre, descripcion, hotel_idInt, vuelo_idInt,
                excursion_idInt, estrellasDouble, personasInt, precioInt);

        procesarImagen(req, idGenerado);

        responderOk(resp, idGenerado);
    }

    private void crearPaqueteSimple(HttpServletRequest req, HttpServletResponse resp) throws Exception {
    	
    	 String nombre = req.getParameter("nombre");
         String descripcion = req.getParameter("descripcion");
         String personasString = req.getParameter("personas");
    	
        String hotel_idString = req.getParameter("hotel_id");
        String vuelo_idString = req.getParameter("vuelo_id");
        String excursion_idString = req.getParameter("excursion_id");

        int hotel_idInt = Integer.parseInt(hotel_idString);
        int vuelo_idInt = Integer.parseInt(vuelo_idString);
        int excursion_idInt = Integer.parseInt(excursion_idString);
        int personasInt = Integer.parseInt(personasString);

        int precioInt = paqueteService.calcularPrecio(hotel_idInt, vuelo_idInt, excursion_idInt,personasInt);

        int idGenerado = paqueteService.saveAndReturnId(
                nombre, descripcion, hotel_idInt, vuelo_idInt, excursion_idInt, 
                0, // estrellas = 0 en paquete simple
                personasInt, 
                precioInt
            );

        procesarImagen(req, idGenerado);

        responderOk(resp, idGenerado);
    }

    private void procesarImagen(HttpServletRequest req, int idGenerado) throws Exception {
        Part imagenPart = req.getPart("imagen");
        if (imagenPart != null && imagenPart.getSize() > 0) {
            String nombreOriginal = Paths.get(imagenPart.getSubmittedFileName()).getFileName().toString();
            String extension = nombreOriginal.substring(nombreOriginal.lastIndexOf(".") + 1).toLowerCase();

            if (!extension.equals("jpg") && !extension.equals("jpeg") && !extension.equals("png")) {
                throw new ServletException("Formato de imagen no permitido.");
            }

            String nombreImagen = "paqueteimg" + idGenerado + "." + extension;
            String uploadPath = req.getServletContext().getRealPath("/images/");
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists())
                uploadDir.mkdir();

            imagenPart.write(uploadPath + File.separator + nombreImagen);
            paqueteService.updateImage(idGenerado, nombreImagen);
        }
    }

    private void responderOk(HttpServletResponse resp, int idGenerado) throws IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("utf-8");
        PrintWriter out = resp.getWriter();
        JsonObject obj = new JsonObject();
        obj.addProperty("estatus", "ok");
        obj.addProperty("mensaje", "Paquete creado con ID: " + idGenerado);
        out.print(obj.toString());
        out.flush();
    }

    private void responderError(HttpServletResponse resp, Exception e) throws IOException {
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