	package com.viajando.service.hotel;
	
	import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.viajando.dao.hotel.HotelDaoImp;
	import com.viajando.dao.hotel.HotelDao;
    import com.viajando.domain.Hotel;
    import com.viajando.service.hotel.HotelService;

public class HotelServiceImp implements HotelService {

	private HotelDao hotelDao = new HotelDaoImp();

	@Override
	public List<Hotel> list() throws Exception {
		return hotelDao.list();
	}

	@Override
	public Hotel findById(int id) throws Exception {
		return hotelDao.findById(id);
	}

	@Override
	public int saveAndReturnId(Hotel hotel) throws Exception {

		return hotelDao.saveAndReturnId(hotel);
	}

	@Override
	public void updateImage(int id, String nombreImagen) throws Exception {
		hotelDao.updateImage(id, nombreImagen);
	}

	@Override
	public void delete(int id) throws Exception {
		hotelDao.delete(id);
	}
}