package com.viajando.dao.hotel;
import com.viajando.domain.Hotel;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


import com.viajando.config.Conexion;
import com.viajando.exception.ErrorException;



public class HotelDaoImp  implements HotelDao  {


private Conexion conexion = Conexion.getInstance();
	
	private static final String queryList = "SELECT id, nombre, destino_id,  destino_value, estrellas, precio, imagen FROM hotel";
	
	private static final String queryConsultarHotel = "SELECT id, nombre, destino_id, destino_value, estrellas, precio, imagen   FROM hotel where id=?";
	
	private static final String queryUpdateImage = "UPDATE hotel SET imagen=? WHERE id=?";
	
	private static final String queryAddHotel = "INSERT INTO hotel (nombre, destuno_id, destino_value, estrellas, precio, imagen) VALUES (?,?,?,?,?,?)";

	private static final String queryDeleteExcursion = "DELETE FROM hotel WHERE id=?";


	
	public List<Hotel> list() throws Exception {
		 ResultSet rs = null;
		 List<Hotel> hotel = null;
		 Hotel producto = null;
		 PreparedStatement st = null;
		 try{
			st = conexion.dameConnection().prepareStatement(queryList);
			rs = st.executeQuery();
			hotel = new ArrayList<Hotel>();
			 while (rs.next()) {
				 producto = new Hotel(rs.getInt(1),rs.getString(2), rs.getInt(3), rs.getString(4), rs.getDouble(5), rs.getInt(6), rs.getString(7));
				 hotel.add(producto);
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
		 
		return hotel;
	}

	
	public Hotel findById(int id) throws Exception {
		 ResultSet rs = null;
		 PreparedStatement st = null;
		 try{
			st = conexion.dameConnection().prepareStatement(queryConsultarHotel);
			st.setInt(1, id);
			rs = st.executeQuery();
			if (rs.next()) {
				return new Hotel(rs.getInt(1),rs.getString(2), rs.getInt(3), rs.getString(4), rs.getDouble(5), rs.getInt(6), rs.getString(7));
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
	    public int saveAndReturnId( String nombre, int destino_id, String destino_value, double estrellas, int precio) throws Exception {
	        PreparedStatement st = null;
	        ResultSet rs = null;
	        int idGenerado = -1;

	        try {
	            st = conexion.dameConnection().prepareStatement(queryAddHotel, Statement.RETURN_GENERATED_KEYS);
	            st.setString(1, nombre);
				st.setInt(2, destino_id);
				st.setString(3, destino_value);
				st.setDouble(4, estrellas);
				st.setInt(5, precio);
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


	public void delete(int id) throws Exception {
		 ResultSet rs = null;
		 PreparedStatement st = null;
		 try{
			st = conexion.dameConnection().prepareStatement(queryDeleteExcursion);
			st.setInt(1, id);
			int rowsAffected = st.executeUpdate();
			if (rowsAffected == 0) {
			    throw new Error("No se encontró el registro");
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
	}


	

	
}
