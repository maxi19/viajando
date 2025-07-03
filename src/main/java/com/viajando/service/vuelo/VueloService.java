
package com.viajando.service.vuelo;

import java.time.LocalDate;
import java.util.List;

import com.viajando.domain.Vuelo;

public interface VueloService {
	
	List<Vuelo> list() throws Exception;
	
	public Vuelo findById(int id) throws Exception;
	
	public int saveAndReturnId(Vuelo vuelo) throws Exception;
	
	public void updateImage(int id, String imagen) throws Exception;
	
	public void delete(int id) throws Exception;
	
	public List<Vuelo> findByDates(LocalDate begin, LocalDate end) throws Exception;
	
}