package com.viajando.dao.excursion;
import java.time.LocalDate;
import java.util.List;

import com.viajando.domain.Excursion;


public interface ExcursionDao {
	
	public List<Excursion> list() throws Exception;
	
	public Excursion findById(int id) throws Exception;
	
    public int saveAndReturnId(String nombre, String descripcion, LocalDate fechaInicio, LocalDate fechaFin, int precio, String destino, double estrellas) throws Exception;
    
    public void updateImage(int id, String nombreImagen) throws Exception;

	public void delete(int id) throws Exception;


}