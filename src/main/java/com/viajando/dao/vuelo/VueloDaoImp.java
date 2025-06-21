package com.viajando.dao.vuelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.viajando.config.Conexion;
import com.viajando.domain.Vuelo;
import com.viajando.exception.ErrorException;
import com.viajando.domain.Destino;

public class VueloDaoImp implements VueloDao {

	private Conexion conexion = Conexion.getInstance();

	private static final String queryAddVuelo = "INSERT INTO vuelo(destino, ida, vuelta, precio, estrellas ,hora_ida, hora_vuelta) VALUES (?,?,?,?,?,?,?)";

	private static final String queryList = "SELECT id, destino, ida, vuelta, precio, estrellas ,hora_ida, hora_vuelta FROM vuelo";

	private static final String queryConsultarVuelo = "SELECT id, destino, ida, vuelta, precio, estrellas ,hora_ida, hora_vuelta FROM vuelo where id=?";

	@Override
	public void crearVuelo(String destino, String ida, String vuelta, int precio, int estrellas, LocalTime hora_ida,
			LocalTime hora_vuelta) throws Exception {

		PreparedStatement ps = null;

		try {
			ps = conexion.dameConnection().prepareStatement(queryAddVuelo);
			ps.setString(1, destino);
			ps.setString(2, ida);
			ps.setString(3, vuelta);
			ps.setInt(4, precio);
			ps.setInt(5, estrellas);
			ps.setTime(6, Time.valueOf(hora_ida));
			ps.setTime(7, Time.valueOf(hora_vuelta));

		} catch (Exception e) {

		}

	}

	@Override
	public List<Vuelo> listarVuelos() throws Exception {

		List<Vuelo> vuelos = null;
		PreparedStatement st = null;
		ResultSet rs = null;

		try {
			st = conexion.dameConnection().prepareStatement(queryList);
			rs = st.executeQuery();

			while (rs.next()) {
				vuelos.add(new Vuelo(rs.getInt(1), rs.getString(2), rs.getDate(3), rs.getDate(4), rs.getInt(5),
						rs.getInt(6), rs.getTime(7), rs.getTime(8)));
			}

		} catch (SQLException e) {
			throw new Exception("Error al listar vuelos: " + e.getMessage(), e);
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
				}
			if (st != null)
				try {
					st.close();
				} catch (SQLException e) {
				}

		}

		return vuelos;
	}

	@Override
	public Vuelo findById(int id) throws Exception {

		ResultSet rs = null;
		PreparedStatement st = null;

		try {
			st = conexion.dameConnection().prepareStatement(queryConsultarVuelo);
			st.setInt(1, id);
			rs = st.executeQuery();

			if (rs.next()) {
				return new Vuelo(rs.getInt(1), rs.getString(2), rs.getDate(3), rs.getDate(4), rs.getInt(5),
						rs.getInt(6), rs.getTime(7), rs.getTime(8));
			}
		} catch (Exception e) {
			throw new ErrorException("Hubo un error al realizar la consulta", e);

		} finally {
			try {
				st.close();
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		return null;
	}
}