package com.viajando.service.reserva;

import java.util.List;
import java.util.Map;

import com.viajando.domain.Reserva;
import com.viajando.domain.dto.ReservaResumen;


public interface ReservaService {
	
	public List<Reserva> list() throws Exception;

	public void save(String identificador, String nombre, String apellido, String sexo, int DNI, String tipo_servicio, int id_vuelo,
			int id_hotel, int id_excursion, int id_paquete, int precio) throws Exception;

	void save(Reserva r) throws Exception;

	List<Reserva> buscarPorTipo(String tipo) throws Exception;

	public Map<String, Integer> resumenPorTipo() throws Exception;


	
}
