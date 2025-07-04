package com.viajando.service;

import java.util.List;

import com.viajando.dao.DestinoDao;
import com.viajando.domain.Destino;

public class DestinoServiceImp implements DestinoService {

	private DestinoDao destinoDao = new DestinoDao();
	
	@Override
	public void addDestino(Destino destino) throws Exception {
		destinoDao.add(destino);
	}

	@Override
	public List<Destino> list() throws Exception {
		return destinoDao.list();
	}

	@Override
	public void delete(int id) throws Exception {
		destinoDao.delete(id);
		
	}


}
