package com.viajando.dao.hotel;

import java.time.LocalDate;
import java.util.List;

import javax.servlet.http.HttpServletRequest;


import com.viajando.domain.Hotel;

public interface HotelDao {
	List<Hotel> list() throws Exception;
	Hotel findById(int id) throws Exception;
	int saveAndReturnId(Hotel hotel) throws Exception;
	void updateImage(int id, String nombreImagen) throws Exception;
	void delete(int id) throws Exception;
	void crearHabitaciones(int hotelId, int stock, HttpServletRequest req) throws Exception;
}