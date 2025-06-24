package com.viajando.service.vuelo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import com.viajando.dao.vuelo.VueloDao;
import com.viajando.dao.vuelo.VueloDaoImp;
import com.viajando.domain.Vuelo;

public class VueloServiceImp implements VueloService {
	private VueloDao vueloDao = new VueloDaoImp();

	@Override
	public List<Vuelo> listar() throws Exception {
		return vueloDao.listar();
	}

	@Override
	public Vuelo buscarPorId(int id) throws Exception {
		return vueloDao.buscarPorId(id);
	}


    @Override
	public void guardar(String destino, LocalDate ida, LocalDate vuelta, int precio, double estrellas,LocalTime horaIda, LocalTime horaVuelta) throws Exception {
    	vueloDao.guardar(destino, ida, vuelta, precio, estrellas, horaIda, horaVuelta);
	}
    
	@Override
	public void eliminar(int id) throws Exception {
		vueloDao.eliminar(id);
	}

	
}
