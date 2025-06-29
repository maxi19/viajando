	package com.viajando.service.hotel;
	
	import java.util.List;
	
	import com.viajando.dao.hotel.HotelDaoImp;
	import com.viajando.dao.hotel.HotelDao;
    import com.viajando.domain.Hotel;
    import com.viajando.service.hotel.HotelService;
    
	public class HotelServiceImp implements HotelService{
	
		private HotelDaoImp hotelDao = new HotelDaoImp();
		
		@Override
		public List<Hotel> list() throws Exception {
			// TODO Auto-generated method stub
			return hotelDao.list();
		}
	
		@Override
		public Hotel findById(int id) throws Exception {
			// TODO Auto-generated method stub
			return hotelDao.findById(id);
		}
	
		@Override
		public int saveAndReturnId(String nombre, int destino_id, double estrellas, int precio, int stock)
				throws Exception {
			// TODO Auto-generated method stub
			return hotelDao.saveAndReturnId(nombre, destino_id, estrellas, precio, stock);
		}
	
		@Override
		public void updateImage(int id, String nombreImagen) throws Exception {
			// TODO Auto-generated method stub
			hotelDao.updateImage(id,nombreImagen);
		}
	
		@Override
		public void delete(int id) throws Exception {
			// TODO Auto-generated method stub
			hotelDao.delete(id);
		}
	
		
		
	}
