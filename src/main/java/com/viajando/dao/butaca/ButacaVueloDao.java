package com.viajando.dao.butaca;

import java.sql.SQLException;
import java.util.List;

import com.viajando.domain.ButacaVuelo;

public interface ButacaVueloDao {
	List<ButacaVuelo> getButacasPorVuelo(int vueloId) throws Exception;
	boolean estaDisponible(int vueloId, int asiento) throws Exception;
	void marcarOcupado(int vueloId, int asiento) throws Exception;
}