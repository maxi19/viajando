package com.viajando.dao.hotel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.viajando.config.Conexion;
import com.viajando.dao.DestinoDao;
import com.viajando.domain.Destino;
import com.viajando.domain.Hotel;
import com.viajando.exception.ErrorException;

public class HotelDaoImp implements HotelDao {

	private Conexion conexion = Conexion.getInstance();
	private DestinoDao destinoDao = new DestinoDao();


	private static final String queryList = 
	    "SELECT h.id, h.nombre, h.estrellas, h.precio, h.imagen, h.stock, " +
	    "d.id AS destino_id, d.nombre AS destino_nombre, d.pais AS destino_pais, d.precio AS destino_precio " +
	    "FROM hotel h JOIN destinos d ON h.destino_id = d.id";

	private static final String queryConsultarHotel = "SELECT * FROM hotel WHERE id=?";
	private static final String queryUpdateImage = "UPDATE hotel SET imagen=? WHERE id=?";
	private static final String queryAddHotel = "INSERT INTO hotel (nombre, destino_id, estrellas, precio, stock) VALUES (?, ?, ?, ?, ?)";
	private static final String queryDeleteHotel = "DELETE FROM hotel WHERE id=?";

	@Override
	public List<Hotel> list() throws Exception {
		List<Hotel> hoteles = new ArrayList<>();

		try (PreparedStatement st = conexion.dameConnection().prepareStatement(queryList);
		     ResultSet rs = st.executeQuery()) {

			while (rs.next()) {
				Destino destino = new Destino(
					rs.getInt("destino_id"),
					rs.getString("destino_nombre"),
					rs.getString("destino_pais"),
					rs.getInt("destino_precio")
				);

				Hotel hotel = new Hotel(
					rs.getInt("id"),
					rs.getString("nombre"),
					destino,
					rs.getDouble("estrellas"),
					rs.getInt("precio"),
					rs.getString("imagen"),
					rs.getInt("stock")
				);

				hoteles.add(hotel);
			}

		} catch (Exception e) {
			System.out.println("Error al listar hoteles: " + e.getMessage());
			e.printStackTrace();
		}

		return hoteles;
	}

	@Override
	public Hotel findById(int id) throws Exception {
		 ResultSet rs = null;
		 PreparedStatement st = null;
		 try{
			st = conexion.dameConnection().prepareStatement(queryConsultarHotel);
			st.setInt(1, id);
			rs = st.executeQuery();
			if (rs.next()) {
				int destinoId = rs.getInt("destino_id");
				Destino destino = destinoDao.getOne(destinoId);

				return new Hotel (rs.getInt("id"),
						rs.getString("nombre"),
						destino,
					    rs.getDouble("estrellas"),
					    rs.getInt("precio"),
					    rs.getString("imagen"),
			            rs.getInt("stock"));

			}

		 }catch (Exception e) {
				throw new ErrorException("Hubo un error al realizar la consulta", e);
		}finally {
			try {
				st.close();
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return null;
	}


	 @Override
	    public int saveAndReturnId( String nombre, int destino_id, double estrellas, int precio, int stock) throws Exception {
	        PreparedStatement st = null;
	        ResultSet rs = null;
	        int idGenerado = -1;

	        try {
	            st = conexion.dameConnection().prepareStatement(queryAddHotel, Statement.RETURN_GENERATED_KEYS);
	            st.setString(1, nombre);
				st.setInt(2, destino_id);
				st.setDouble(3, estrellas);
				st.setInt(4, precio);
				st.setInt(5, stock);
	            st.executeUpdate();

	            rs = st.getGeneratedKeys(); // pide la llave generada automaticamente, osea la primary key
	            if (rs.next()) {
	                idGenerado = rs.getInt(1); 
	            }
	        } finally {
	            if (st != null) st.close();
	            if (rs != null) rs.close();
	        }
	        return idGenerado;
	    }

	   
	
	    
	  

	    @Override
	    public void updateImage(int id, String nombreImagen) throws Exception {
	        PreparedStatement st = null;
	        try {
	            st = conexion.dameConnection().prepareStatement(queryUpdateImage);
	            st.setString(1, nombreImagen);
	            st.setInt(2, id);
	            st.executeUpdate();
	        } finally {
	            if (st != null) st.close();
	        }
	    } 

	
	
	private void finalizarConexion(PreparedStatement st) {
		try {
			if(st != null)st.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void delete(int id) throws Exception {
		 ResultSet rs = null;
		 PreparedStatement st = null;
		 try{
			st = conexion.dameConnection().prepareStatement(queryDeleteHotel);
			st.setInt(1, id);
			 System.out.println(id);
			int rowsAffected = st.executeUpdate();
			if (rowsAffected == 0) {
			    throw new Error("No se encontró el registro");

			}

		 }catch (Exception e) {
				throw new ErrorException("Hubo un error al realizar la consulta", e);
		}finally {
			try {
				st.close();
			//	rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
	}


	

	
}
