package com.viajando.service.vuelo;

import java.sql.Time;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

import com.viajando.domain.Vuelo;

public interface VueloService {
	
public void crearVuelo(String destino, String ida, String vuelta, int precio, int estrellas, LocalTime hora_ida, LocalTime hora_vuelta) throws Exception;
	
	public List<Vuelo> listarVuelos() throws Exception;

	public Vuelo findById(int int1);
	
	

}
