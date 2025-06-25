package com.viajando.dao.hotel;

import java.time.LocalDate;
import java.util.List;

import com.viajando.domain.Hotel;

public interface HotelDao {

	
	public List<Hotel> list() throws Exception;
	
	public Hotel findById(int id) throws Exception;
	
    public int saveAndReturnId( String nombre, int destino_id, String destino_value, double estrellas, int precio) throws Exception;
    
    public void updateImage(int id, String nombreImagen) throws Exception;

	public void delete(int id) throws Exception;


}
