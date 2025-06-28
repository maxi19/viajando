package com.viajando.controller.butaca;

import com.google.gson.Gson;
import com.viajando.dao.butaca.ButacaVueloDao;
import com.viajando.dao.butaca.ButacaVueloDaoImp;
import com.viajando.domain.ButacaVuelo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/butacasPorVuelo")
public class ButacasPorVueloController extends HttpServlet {

    private ButacaVueloDao dao = new ButacaVueloDaoImp();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        
        String idVueloStr = req.getParameter("vuelo_id");
        resp.setContentType("application/json");

        try {
            int vueloId = Integer.parseInt(idVueloStr);
            List<ButacaVuelo> butacas = dao.getButacasPorVuelo(vueloId);

            String json = new Gson().toJson(butacas);
            resp.getWriter().write(json);

        } catch (Exception e) {
            e.printStackTrace();
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().write("{\"error\": \"No se pudieron obtener las butacas\"}");
        }
    }
}