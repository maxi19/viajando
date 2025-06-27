package com.viajando.dao;

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
	private static final String queryGetOne = "SELECT id, id_marca, titulo, nombre, descripcion, id_categoria, stock, precio, origen, portada FROM productos WHERE id = ?";
	private static final String queryDelete = "DELETE from productos WHERE id=?";
	private static final String queryEdit = "UPDATE productos SET stock = ? WHERE id= ?";
	private static final String queryInsert = "INSERT INTO productos(id_marca,titulo,nombre,descripcion,id_categoria,stock,precio,origen,portada) VALUES (?,?,?,?,?,?,?,?,?)";
	
	private static final String queryEditFlag = "UPDATE productos SET portada = ?  WHERE id = ? ";
	
	
	@Override
	public void add(Destino t) throws ErrorException {

		
	}

	@Override
	public void delete(Integer i) throws ErrorException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void edit(Destino t) throws ErrorException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Destino> list() throws ErrorException {

		 ResultSet rs = null;
		 List<Destino> destinos = null;
		 Destino destino = null;
		 PreparedStatement st = null;
		 try{
			st = conexion.dameConnection().prepareStatement(queryList);
			rs = st.executeQuery();
			destinos = new ArrayList<Destino>();
			 while (rs.next()) {
				destino = new Destino(rs.getInt(1), rs.getString(2), rs.getString(3) ,rs.getInt(4));
				destinos.add(destino);
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
	 
		return destinos;
		
	}
 ///REVISARRR
	@Override
	public Destino getOne(Integer id) throws ErrorException {
	    PreparedStatement st = null;
	    ResultSet rs = null;
	    Destino destino = null;

	    String query = "SELECT id, nombre, pais, precio FROM destinos WHERE id = ?";

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
	        if (rs != null) try { rs.close(); } catch (SQLException ignored) {}
	        if (st != null) try { st.close(); } catch (SQLException ignored) {}
	    }

	    return destino;
	}




}
