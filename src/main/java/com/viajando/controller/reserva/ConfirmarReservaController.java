package com.viajando.controller.reserva;

import com.google.gson.Gson;
import com.viajando.dao.reserva.ReservaDaoImp;
import com.viajando.domain.Reserva;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.UUID;

@WebServlet("/confirmarReserva")
public class ConfirmarReservaController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // Leer el JSON que llega por POST
        BufferedReader reader = req.getReader();
        StringBuilder json = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null) {
            json.append(line);
        }

        // Parsear a objetos Reserva[]
        Gson gson = new Gson();
        Reserva[] reservas = gson.fromJson(json.toString(), Reserva[].class);

        ReservaDaoImp dao = new ReservaDaoImp();
        String identificador = UUID.randomUUID().toString(); // ID único para el grupo

        try {
            for (Reserva reserva : reservas) {
                reserva.setIdentificador(identificador);

                int idServicio = reserva.getServicio_id(); 

                switch (reserva.getTipoServicio()) {
                case "vuelo" -> reserva.setIdVuelo(reserva.getServicio_id());
                case "excursion" -> reserva.setIdExcursion(reserva.getServicio_id());
                case "hotel" -> reserva.setIdHotel(reserva.getServicio_id());
                case "paquete" -> reserva.setIdPaquete(reserva.getServicio_id());
            }

                reserva.setPrecio(1000); // Placeholder, podés reemplazar por precio real
                dao.save(reserva);
            }

            resp.setStatus(HttpServletResponse.SC_OK);

        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            e.printStackTrace();
        }
    }
}