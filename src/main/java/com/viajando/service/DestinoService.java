package com.viajando.service;

import java.util.List;

import com.viajando.domain.Destino;

public interface DestinoService {

	public void addDestino() throws Exception;
	
	public List<Destino> list()throws Exception;
	
}
