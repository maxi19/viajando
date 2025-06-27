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
    public int saveAndReturnId(String nombre, String descripcion, LocalDate fechaInicio, LocalDate fechaFin,
                               int precio,  int destino_id, double estrellas) throws Exception {
        return excursionDao.saveAndReturnId(nombre, descripcion, fechaInicio, fechaFin, precio, destino_id, estrellas);
    }

    @Override
    public void updateImage(int id, String nombreImagen) throws Exception {
        excursionDao.updateImage(id, nombreImagen);
    }
	
	
	@Override
	public void delete(int id) throws Exception {
		excursionDao.delete(id);
	}


	
	
	

}
