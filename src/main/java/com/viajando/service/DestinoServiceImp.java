package com.viajando.service;

import java.util.List;

import com.viajando.dao.DestinoDao;
import com.viajando.domain.Destino;

public class DestinoServiceImp implements DestinoService {

	private DestinoDao destinoDao = new DestinoDao();
	
	@Override
	public void addDestino() throws Exception {
		
	}

	@Override
	public List<Destino> list() throws Exception {
		return destinoDao.list();
	}

}
