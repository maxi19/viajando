package com.viajando.controller.reserva;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.google.gson.Gson;
import com.viajando.domain.Reserva;
import com.viajando.service.reserva.ReservaService;
import com.viajando.service.reserva.ReservaServiceImp;

@WebServlet("/detalleReservasPorTipo")
public class ReservasPorTipoDetalleController extends HttpServlet {

    private ReservaService reservaService = new ReservaServiceImp();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String tipo = req.getParameter("tipo");

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        try (PrintWriter out = resp.getWriter()) {
            if (tipo == null || tipo.trim().isEmpty()) {
                // Devuelve resumen por tipo para el gráfico
                Map<String, Integer> resumen = reservaService.resumenPorTipo();  // <-- lo agregás abajo
                Map<String, Object> response = new HashMap<>();
                response.put("labels", new ArrayList<>(resumen.keySet()));
                response.put("data", new ArrayList<>(resumen.values()));
                out.print(new Gson().toJson(response));
            } else {
                // Devuelve detalle de reservas por tipo
                List<Reserva> lista = reservaService.buscarPorTipo(tipo);
                out.print(new Gson().toJson(lista));
            }
        } catch (Exception e) {
            e.printStackTrace();
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }}