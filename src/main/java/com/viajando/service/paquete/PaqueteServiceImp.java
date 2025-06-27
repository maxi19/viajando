package com.viajando.service.paquete;


import java.time.LocalDate;
import java.util.List;

import com.viajando.dao.paquete.PaqueteDaoImp;

import com.viajando.domain.Paquete;

public class PaqueteServiceImp implements PaqueteService {

	private PaqueteDaoImp  paqueteDao = new PaqueteDaoImp();

	@Override
	public List<Paquete> list() throws Exception {
		return paqueteDao.list();
	}
	
	@Override
	public Paquete findById(int id) throws Exception {
		return paqueteDao.findById(id);
	}

	@Override
	public int saveAndReturnId(String nombre, String descripcion, int hotel_id, int vuelo_id, int excursion_id, double estrellas, int personas,
	        int precio  ) throws Exception {
	    return paqueteDao.saveAndReturnId(nombre, descripcion,hotel_id, vuelo_id, excursion_id, estrellas,personas, precio   );
	}

    @Override
    public void updateImage(int id, String nombreImagen) throws Exception {
    	paqueteDao.updateImage(id, nombreImagen);
    }
	
	
	@Override
	public void delete(int id) throws Exception {
		paqueteDao.delete(id);
	}




	
	
	

}
