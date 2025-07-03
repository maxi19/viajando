package com.viajando.service.vuelo;

import java.time.LocalDate;
import java.util.List;
import com.viajando.dao.vuelo.VueloDao;
import com.viajando.dao.vuelo.VueloDaoImp;
import com.viajando.domain.Vuelo;

public class VueloServiceImp implements VueloService {
	private VueloDao vueloDao = new VueloDaoImp();

	public List<Vuelo> list() throws Exception {
		return vueloDao.list();
	}

	public Vuelo findById(int id) throws Exception {
		return vueloDao.findById(id);
	}

	public int saveAndReturnId(Vuelo vuelo) throws Exception {
		return vueloDao.saveAndReturnId(vuelo);
	}

	public void updateImage(int id, String imagen) throws Exception {
		vueloDao.updateImage(id, imagen);
	}

	public void delete(int id) throws Exception {
		vueloDao.delete(id);
	}

	@Override
	public List<Vuelo> findByDates(LocalDate begin, LocalDate end) throws Exception {
		return vueloDao.findByDate(begin, end);	
	}
}