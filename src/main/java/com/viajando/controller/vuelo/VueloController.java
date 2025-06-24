package com.viajando.controller.vuelo;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.viajando.domain.Vuelo;
import com.viajando.service.vuelo.VueloService;
import com.viajando.service.vuelo.VueloServiceImp;
/**
 * Servlet implementation class VueloController
 */
@WebServlet(urlPatterns = {"/crearVuelo", "/eliminarVuelo", "/listarVuelos"}  )
public class VueloController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    private VueloService vueloService = new VueloServiceImp();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String path = request.getServletPath();

        try {
            switch (path) {
                case "/listarVuelos":
                    List<Vuelo> vuelos = vueloService.listar();
                    request.setAttribute("vuelos", vuelos);
                    request.getRequestDispatcher("/Vuelo/ListarVuelos.jsp").forward(request, response);
                    break;

                case "/eliminarVuelo":
                    int id = Integer.parseInt(request.getParameter("id"));
                    vueloService.eliminar(id);
                    response.sendRedirect("listarVuelos");
                    break;

                default:
                    response.sendError(404, "Ruta GET no encontrada");
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(500, "Error interno del servidor");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String path = request.getServletPath();

        if ("/crearVuelo".equals(path)) {
            try {
            	request.setCharacterEncoding("UTF-8");
            	
                String destino = request.getParameter("destino");
                LocalDate ida = LocalDate.parse(request.getParameter("ida"));
                LocalDate vuelta = LocalDate.parse(request.getParameter("vuelta"));
                int precio = Integer.parseInt(request.getParameter("precio"));
                double estrellas = Double.parseDouble(request.getParameter("estrellas"));
                LocalTime horaIda = LocalTime.parse(request.getParameter("horaIda"));
                LocalTime horaVuelta = LocalTime.parse(request.getParameter("horaVuelta"));

                System.out.println("Datos recibidos para crear vuelo:");
                System.out.println("Destino: " + destino);
                System.out.println("Fecha Ida: " + ida);
                System.out.println("Fecha Vuelta: " + vuelta);
                System.out.println("Precio: " + precio);
                System.out.println("Estrellas: " + estrellas);
                System.out.println("Hora Ida: " + horaIda);
                System.out.println("Hora Vuelta: " + horaVuelta);
                
                
                vueloService.guardar(destino, ida, vuelta, precio, estrellas, horaIda, horaVuelta);

                response.sendRedirect("listarVuelos");

            } catch (Exception e) {
                e.printStackTrace();
                response.sendError(400, "Error al crear el vuelo");
            }
        } else {
            response.sendError(404, "Ruta POST no encontrada");
        }
    }
}
