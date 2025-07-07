package com.viajando.service.reserva;

import java.util.List;
import java.util.Map;

import com.viajando.dao.reserva.ReservaDaoImp;
import com.viajando.domain.Reserva;
import com.viajando.domain.dto.ReservaResumen;

public class ReservaServiceImp implements ReservaService{
	
	private ReservaDaoImp reservaDao = new ReservaDaoImp();


	@Override
	public List<Reserva> list() throws Exception {
		return reservaDao.list();
	}

	@Override
	public void save(String identificador, String nombre, String apellido, String sexo, int DNI, String tipo_servicio, int id_vuelo,
			int id_hotel, int id_excursion, int id_paquete, int precio) throws Exception {
		reservaDao.save(identificador, nombre, apellido, sexo, DNI, tipo_servicio, id_vuelo, id_hotel, id_excursion, id_paquete, precio);
	}

	@Override
	public void save(Reserva r) throws Exception {
reservaDao.save(r);		
	}
	
	@Override
	public List<Reserva> buscarPorTipo(String tipo) throws Exception {
	    return reservaDao.buscarPorTipo(tipo);
	}
	
	public Map<String, Integer> resumenPorTipo() throws Exception {
	    return reservaDao.resumenPorTipo();
	}

}
