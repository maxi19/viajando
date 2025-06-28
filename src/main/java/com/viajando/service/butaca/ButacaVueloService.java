package com.viajando.service.butaca;

import java.sql.SQLException;
import java.util.List;

import com.viajando.domain.ButacaVuelo;

public interface ButacaVueloService {
	   List<ButacaVuelo> listarPorVuelo(int vueloId) throws Exception;
	    boolean asientoDisponible(int vueloId, int asiento) throws Exception;
	    void ocuparAsiento(int vueloId, int asiento) throws Exception;
}
