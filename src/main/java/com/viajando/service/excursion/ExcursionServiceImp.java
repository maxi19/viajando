package com.viajando.service.excursion;
import java.time.LocalDate;
import java.util.List;

import com.viajando.dao.excursion.ExcursionDaoImp;
import com.viajando.dao.excursion.ExcursionDao;
import com.viajando.domain.Excursion;

public class ExcursionServiceImp implements ExcursionService{
	
	private ExcursionDaoImp excursionDao = new ExcursionDaoImp();

	@Override
	public List<Excursion> list() throws Exception {
		return excursionDao.list();
	}
	
	@Override
	public Excursion findById(int id) throws Exception {
		return excursionDao.findById(id);
	}

	@Override
	public void save(String nombre, String descripcion, LocalDate fecha_inicio, LocalDate fecha_fin, int precio,
			String destino, double estrellas) throws Exception {
		excursionDao.save(nombre, descripcion, fecha_inicio, fecha_fin, precio, destino, estrellas);
		
	}
	
	
	@Override
	public void delete(int id) throws Exception {
		excursionDao.delete(id);
	}


	
	
	

}
