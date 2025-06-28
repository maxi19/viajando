package com.viajando.service.butaca;
import java.sql.SQLException;
import java.util.List;

import com.viajando.dao.butaca.ButacaVueloDao;
import com.viajando.dao.butaca.ButacaVueloDaoImp;
import com.viajando.domain.ButacaVuelo;


public class ButacaVueloServiceImp implements ButacaVueloService {

    private ButacaVueloDao dao = new ButacaVueloDaoImp();

    @Override
    public List<ButacaVuelo> listarPorVuelo(int vueloId) throws Exception {
        return dao.getButacasPorVuelo(vueloId);
    }

    @Override
    public boolean asientoDisponible(int vueloId, int asiento) throws Exception {
        return dao.estaDisponible(vueloId, asiento);
    }

    @Override
    public void ocuparAsiento(int vueloId, int asiento) throws Exception {
        dao.marcarOcupado(vueloId, asiento);
    }
}