package com.viajando.service.reserva;

import java.util.List;

import com.viajando.domain.Reserva;


public interface ReservaService {
	
	public List<Reserva> list() throws Exception;

	public void save(String identificador, String nombre, String apellido, String sexo, int DNI, String tipo_servicio, int id_vuelo,
			int id_hotel, int id_excursion, int id_paquete, int precio) throws Exception;

	void save(Reserva r) throws Exception;

	
}
