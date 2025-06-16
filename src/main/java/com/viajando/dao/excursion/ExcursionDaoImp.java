package com.viajando.dao.excursion;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.viajando.config.Conexion;
import com.viajando.domain.Excursion;
import com.viajando.exception.ErrorException;
import com.viajando.domain.Excursion;

public class ExcursionDaoImp implements ExcursionDao {

	private Conexion conexion = Conexion.getInstance();

	private static final String queryConsultarExcursion = "SELECT id, nombre, descripcion, fecha_inicio, fecha_fin, precio, destino, estrellas FROM excursion where id=?";

	private static final String queryAddExcursion = "INSERT INTO excursion (nombre, descripcion, fecha_inicio, fecha_fin, precio, destino, estrellas) VALUES (?,?,?,?,?,?,?)";

	private static final String queryDeleteExcursion = "DELETE FROM excursion WHERE id=?";

	private static final String queryList = "SELECT id, nombre, descripcion, fecha_inicio, fecha_fin, precio, destino, estrellas FROM excursion";

	@Override
	public List<Excursion> list() throws Exception {
		ResultSet rs = null;
		PreparedStatement st = null;

		List<Excursion> excursion = new ArrayList<Excursion>();

		try {
			st = conexion.dameConnection().prepareStatement(queryList);
			rs = st.executeQuery();

			while (rs.next()) {
				excursion.add(new Excursion(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDate(4).toLocalDate(),
						rs.getDate(5).toLocalDate(), rs.getInt(6), rs.getString(7), rs.getDouble(8)));
			}

		} catch (Exception e) {
			System.out.println(e.getCause());
		} finally {
			st.close();
			rs.close();
		}

		return excursion;
	}

	@Override
	public Excursion findById(int id) throws Exception {
		ResultSet rs = null;
		PreparedStatement st = null;
		try {
			st = conexion.dameConnection().prepareStatement(queryConsultarExcursion);
			st.setInt(1, id);
			rs = st.executeQuery();
			if (rs.next()) {
				return new Excursion(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDate(4).toLocalDate(),
						rs.getDate(5).toLocalDate(), rs.getInt(6), rs.getString(7), rs.getDouble(8));
			}

		} catch (Exception e) {
			throw new ErrorException("Hubo un error al realizar la consulta", e);
		} finally {
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
	public void save(String nombre, String descripcion, LocalDate fecha_inicio, LocalDate fecha_fin, int precio,
			String destino, double estrellas) throws Exception {
		 ResultSet rs = null;
		 PreparedStatement st = null;
		 try{
			st = conexion.dameConnection().prepareStatement(queryAddExcursion);
			st.setString(1, nombre);
			st.setString(2, descripcion);
			st.setDate(3, java.sql.Date.valueOf(fecha_inicio)); //convierte de LocalDate a Date
			st.setDate(4, java.sql.Date.valueOf(fecha_fin));
			st.setInt(5, precio);
			st.setString(6, destino);
			st.setDouble(7, estrellas);
			
			st.executeUpdate();
		 }catch (Exception e) {
				throw new ErrorException("Hubo un error al realizar la consulta", e);
		}finally {
			try {
				st.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}		
	}
		 
		 
	@Override
	public void delete(int id) throws Exception {
		 ResultSet rs = null;
		 PreparedStatement st = null;
		 try{
			st = conexion.dameConnection().prepareStatement(queryDeleteExcursion);
			st.setInt(1, id);
			rs = st.executeQuery();
			if (!rs.next()) {
				throw new Error("No se encontro el registro");
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



