package com.viajando.service.vuelo;

import java.sql.Time;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

import com.viajando.dao.vuelo.VueloDaoImp;
import com.viajando.dao.vuelo.VueloDao;
import com.viajando.domain.Vuelo;

public class VueloServiceImp implements VueloService{
	
	private VueloDao vueloDao = new VueloDaoImp();

	@Override
	public void crearVuelo(String destino, String ida, String vuelta, int precio, int estrellas, LocalTime hora_ida, LocalTime hora_vuelta) throws Exception {
		vueloDao.crearVuelo(destino, ida, vuelta, precio, estrellas, hora_vuelta, hora_vuelta);
		
	}

	@Override
	public List<Vuelo> listarVuelos() throws Exception {
		return vueloDao.listarVuelos();
	}

	@Override
	public Vuelo findById(int int1) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
