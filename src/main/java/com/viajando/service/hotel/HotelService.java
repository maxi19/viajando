package com.viajando.service.hotel;

import java.util.List;

import com.viajando.domain.Hotel;

import javax.servlet.http.HttpServletRequest;

public interface HotelService {
	List<Hotel> list() throws Exception;
	Hotel findById(int id) throws Exception;
	int saveAndReturnId(Hotel hotel) throws Exception;
	void updateImage(int id, String nombreImagen) throws Exception;
	void delete(int id) throws Exception;
}
