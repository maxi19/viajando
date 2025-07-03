package com.viajando.dao.vuelo;


import java.time.LocalDate;
import java.util.List;

import com.viajando.domain.Vuelo;

public interface VueloDao {
	
	List<Vuelo> list() throws Exception;
	
	Vuelo findById(int id) throws Exception;
	
	int saveAndReturnId(Vuelo vuelo) throws Exception;
	
	void updateImage(int id, String imagen) throws Exception;
	
	void delete(int id) throws Exception;
	
	List<Vuelo> findByDate(LocalDate begin,LocalDate end) throws Exception;
}