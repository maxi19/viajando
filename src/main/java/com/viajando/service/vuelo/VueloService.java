package com.viajando.service.vuelo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import com.viajando.domain.Vuelo;

public interface VueloService {
	List<Vuelo> listar() throws Exception;
	Vuelo buscarPorId(int id) throws Exception;
	void guardar(String destino, LocalDate ida, LocalDate vuelta, int precio, double estrellas, LocalTime horaIda, LocalTime horaVuelta) throws Exception;
	void eliminar(int id) throws Exception;
}
