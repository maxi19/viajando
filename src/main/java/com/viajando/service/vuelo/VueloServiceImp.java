package com.viajando.service.vuelo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import com.viajando.dao.vuelo.VueloDao;
import com.viajando.dao.vuelo.VueloDaoImp;
import com.viajando.domain.Vuelo;

public class VueloServiceImp implements VueloService {
	private VueloDao vueloDao = new VueloDaoImp();

	private VueloDaoImp dao = new VueloDaoImp();

	public List<Vuelo> list() throws Exception {
		return dao.list();
	}

	public Vuelo findById(int id) throws Exception {
		return dao.findById(id);
	}

	public int saveAndReturnId(Vuelo vuelo) throws Exception {
		return dao.saveAndReturnId(vuelo);
	}

	public void updateImage(int id, String imagen) throws Exception {
		dao.updateImage(id, imagen);
	}

	public void delete(int id) throws Exception {
		dao.delete(id);
	}
}