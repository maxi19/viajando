package com.viajando.service.excursion;
import java.time.LocalDate;
import java.util.List;

import com.viajando.domain.Excursion;

public interface ExcursionService {
	
	public List<Excursion> list() throws Exception;

	public Excursion findById(int id) throws Exception;
	
	public void delete (int id) throws Exception;
	
	public void save(String nombre,String descripcion, LocalDate fecha_inicio, LocalDate fecha_fin, int precio, String destino, double estrellas) throws Exception;
	
	
}