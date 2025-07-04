package com.viajando.service;

import java.util.List;

import com.viajando.domain.Destino;

public interface DestinoService {
	
	void addDestino(Destino destino) throws Exception;
	
	public List<Destino> list()throws Exception;

	public void delete (int id) throws Exception;

	
}
