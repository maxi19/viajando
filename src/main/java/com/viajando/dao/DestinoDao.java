package com.viajando.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.viajando.config.Conexion;
import com.viajando.domain.Destino;
import com.viajando.exception.ErrorException;

public class DestinoDao implements DaoBase<Integer, Destino>{

	private Conexion conexion = Conexion.getInstance();
	private static final String queryList = "SELECT id, nombre, pais, precio from destinos";
    private static final String queryInsertDestino = "INSERT INTO destinos (nombre, pais, precio) VALUES (?, ?, ?)";
    private static final String query = "SELECT id, nombre, pais, precio FROM destinos WHERE id = ?";
    private static final String queryDelete = "DELETE FROM destinos where id=?";



	
	@Override
	public void add(Destino t) throws ErrorException {
		PreparedStatement st = null;
		Destino destino = null;
    try {
        st = conexion.dameConnection().prepareStatement(queryInsertDestino);
		st.setString(1, t.getNombre());
        st.setString(2, t.getPais());
        st.setInt(3, t.getPrecio());
        st.executeUpdate();
    } catch (Exception e) {
        throw new ErrorException("Error al insertar el destino", e);
    } finally {
        if (st != null) {
            try {
                st.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
		

	@Override
	public void delete(Integer i) throws ErrorException {
		  try (PreparedStatement st = conexion.dameConnection().prepareStatement(queryDelete)) {
	            st.setInt(1, i);
	            int rowsAffected = st.executeUpdate();
	            if (rowsAffected == 0) {
	                throw new Error("No se encontró el registro");
	            }
	        } catch (Exception e) {
	            throw new ErrorException("Hubo un error al realizar la consulta", e);
	        }
	    }

	@Override
	public void edit(Destino t) throws ErrorException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Destino> list() throws ErrorException {
	    ResultSet rs = null;
	    PreparedStatement st = null;
	    List<Destino> destinos = new ArrayList<>();

	    try {
	        st = conexion.dameConnection().prepareStatement(queryList);
	        rs = st.executeQuery();

	        while (rs.next()) {
	            Destino destino = new Destino(
	                rs.getInt("id"),
	                rs.getString("nombre"),
	                rs.getString("pais"),
	                rs.getInt("precio")
	            );
	            destinos.add(destino);
	        }
	    } catch (Exception e) {
	        throw new ErrorException("Hubo un error al realizar la consulta", e);
	    } finally {
	        try {
	            if (rs != null) rs.close();
	            if (st != null) st.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    return destinos;
	}
 ///REVISARRR
	public Destino getOne(Integer id) throws ErrorException {
	    PreparedStatement st = null;
	    ResultSet rs = null;
	    Destino destino = null;

	    try {
	        st = conexion.dameConnection().prepareStatement(query);
	        st.setInt(1, id);
	        rs = st.executeQuery();

	        if (rs.next()) {
	            destino = new Destino(
	                rs.getInt("id"),
	                rs.getString("nombre"),
	                rs.getString("pais"),
	                rs.getInt("precio")
	            );
	        }
	    } catch (Exception e) {
	        throw new ErrorException("Error al obtener el destino", e);
	    } finally {
	        try {
	            if (rs != null) rs.close();
	            if (st != null) st.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    return destino;
	}



}
