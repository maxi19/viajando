package com.viajando.dao.butaca;

import com.viajando.config.Conexion;
import com.viajando.dao.butaca.ButacaVueloDao;
import com.viajando.domain.ButacaVuelo;
import com.viajando.exception.ErrorException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ButacaVueloDaoImp implements ButacaVueloDao {

	private Conexion conexion = Conexion.getInstance();

	private static final String QUERY_LISTAR_POR_VUELO = "SELECT * FROM butaca_vuelo WHERE vuelo_id = ?";
	private static final String QUERY_DISPONIBLE = "SELECT estado FROM butaca_vuelo WHERE vuelo_id = ? AND asiento = ?";
	private static final String QUERY_OCUPAR = "UPDATE butaca_vuelo SET estado = 'ocupado' WHERE vuelo_id = ? AND asiento = ?";

	@Override
	public List<ButacaVuelo> getButacasPorVuelo(int vueloId) throws Exception {
		PreparedStatement st = null;
		ResultSet rs = null;
		List<ButacaVuelo> lista = new ArrayList<>();

		try {
			st = conexion.dameConnection().prepareStatement(QUERY_LISTAR_POR_VUELO);
			st.setInt(1, vueloId);
			rs = st.executeQuery();

			while (rs.next()) {
				ButacaVuelo b = new ButacaVuelo();
				b.setId(rs.getInt("id"));
				b.setVueloId(rs.getInt("vuelo_id"));
				b.setAsiento(rs.getInt("asiento"));
				b.setEstado(rs.getString("estado"));
				lista.add(b);
			}
		} catch (Exception e) {
			throw new ErrorException("Error al consultar butacas por vuelo", e);
		} finally {
			if (rs != null) rs.close();
			if (st != null) st.close();
		}
		return lista;
	}

	@Override
	public boolean estaDisponible(int vueloId, int asiento) throws Exception {
		PreparedStatement st = null;
		ResultSet rs = null;

		try {
			st = conexion.dameConnection().prepareStatement(QUERY_DISPONIBLE);
			st.setInt(1, vueloId);
			st.setInt(2, asiento);
			rs = st.executeQuery();

			if (rs.next()) {
				return rs.getString("estado").equalsIgnoreCase("disponible");
			}
			return false;

		} catch (Exception e) {
			throw new ErrorException("Error al verificar disponibilidad de butaca", e);
		} finally {
			if (rs != null) rs.close();
			if (st != null) st.close();
		}
	}

	@Override
	public void marcarOcupado(int vueloId, int asiento) throws Exception {
		PreparedStatement st = null;

		try {
			st = conexion.dameConnection().prepareStatement(QUERY_OCUPAR);
			st.setInt(1, vueloId);
			st.setInt(2, asiento);
			st.executeUpdate();

		} catch (Exception e) {
			throw new ErrorException("Error al actualizar estado de butaca", e);
		} finally {
			if (st != null) st.close();
		}
	}


}