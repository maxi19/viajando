package com.viajando.service.paquete;

import java.time.LocalDate;
import java.util.List;

import com.viajando.domain.Paquete;

public interface PaqueteService {

	public List<Paquete> list() throws Exception;

	public Paquete findById(int id) throws Exception;
	
	public void delete (int id) throws Exception;
	
   public int saveAndReturnId(String nombre, String descripcion,int hotel_id, int vuelo_id, int excursion_id,
             double estrellas, int personas, int precio   ) throws Exception;
   
   void updateImage(int id, String nombreImagen) throws Exception;
}
