package com.viajando.service.paquete;


import java.time.LocalDate;
import java.util.List;

import com.viajando.dao.paquete.PaqueteDaoImp;
import com.viajando.dao.hotel.HotelDao;
import com.viajando.dao.hotel.HotelDaoImp;
import com.viajando.dao.vuelo.VueloDao;
import com.viajando.dao.vuelo.VueloDaoImp;
import com.viajando.dao.excursion.ExcursionDao;
import com.viajando.dao.excursion.ExcursionDaoImp;

import com.viajando.domain.Paquete;

public class PaqueteServiceImp implements PaqueteService {
	private HotelDao hotelDao = new HotelDaoImp();
	private VueloDao vueloDao = new VueloDaoImp();
	private ExcursionDao excursionDao = new ExcursionDaoImp();
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

	@Override
	public int saveAndReturnIdSimple(String nombre, String descripcion, int hotel_id, int vuelo_id, int excursion_idI, int precio, int personas)
			throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int calcularPrecio(int hotel_id, int vuelo_id, int excursion_id, int personas) throws Exception {
	    int total = 0;
	    
	    if (hotel_id > 0) {
	        total += hotelDao.findById(hotel_id).getPrecio();
	    }

	    if (vuelo_id > 0) {
	        total += vueloDao.findById(vuelo_id).getPrecio();
	    }

	    if (excursion_id > 0) {
	        total += excursionDao.findById(excursion_id).getPrecio();
	    }

	    return total * personas;
	}




	
	
	

}
