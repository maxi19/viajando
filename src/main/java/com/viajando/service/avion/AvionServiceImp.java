package com.viajando.service.avion;

import java.util.List;

import com.viajando.domain.Avion;

import com.viajando.dao.avion.AvionDao;
import com.viajando.dao.avion.AvionDaoImp;

public class AvionServiceImp implements AvionService {
	
	  private AvionDao avionDao = new AvionDaoImp();

	    @Override
	    public void addAvion() throws Exception {
	        // Método vacío por ahora
	    }

	    @Override
	    public List<Avion> list() throws Exception {
	        return avionDao.list();
	    }

}
