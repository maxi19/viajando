package com.viajando.dao.vuelo;

import java.sql.Connection;
import java.sql.Date;
import java.time.LocalDate;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import com.viajando.config.Conexion;
import com.viajando.domain.Destino;
import com.viajando.domain.Vuelo;
import com.viajando.dao.DestinoDao;
import com.viajando.exception.ErrorException;

public class VueloDaoImp implements VueloDao {

	private Conexion conexion = Conexion.getInstance();
	private DestinoDao destinoDao = new DestinoDao();

	private static final String QUERY_LIST = "SELECT * FROM vuelo";
	private static final String QUERY_FIND = "SELECT * FROM vuelo WHERE id = ?";
	private static final String QUERY_INSERT = "INSERT INTO vuelo (nombre, destino_id, fecha_inicio, fecha_fin, precio, estrellas, hora_ida, hora_vuelta, id_avion, imagen) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String QUERY_UPDATE_IMG = "UPDATE vuelo SET imagen = ? WHERE id = ?";
	private static final String QUERY_DELETE = "DELETE FROM vuelo WHERE id = ?";

	@Override
	public List<Vuelo> list() throws Exception {
		List<Vuelo> vuelos = new ArrayList<>();
		try (PreparedStatement st = conexion.dameConnection().prepareStatement(QUERY_LIST);
		     ResultSet rs = st.executeQuery()) {
			while (rs.next()) {
				Destino destino = destinoDao.getOne(rs.getInt("destino_id"));
				Vuelo vuelo = new Vuelo(
					rs.getInt("id"),
					rs.getString("nombre"),
					destino,
					rs.getDate("fecha_inicio").toLocalDate(),
					rs.getDate("fecha_fin").toLocalDate(),
					rs.getInt("precio"),
					rs.getDouble("estrellas"),
					rs.getTime("hora_ida").toLocalTime(),
					rs.getTime("hora_vuelta").toLocalTime(),
					rs.getInt("id_avion"),
					rs.getString("imagen")
				);
				vuelos.add(vuelo);
			}
		}
		return vuelos;
	}

	@Override
	public Vuelo findById(int id) throws Exception {
		try (PreparedStatement st = conexion.dameConnection().prepareStatement(QUERY_FIND)) {
			st.setInt(1, id);
			try (ResultSet rs = st.executeQuery()) {
				if (rs.next()) {
					Destino destino = destinoDao.getOne(rs.getInt("destino_id"));
					return new Vuelo(
						rs.getInt("id"),
						rs.getString("nombre"),
						destino,
						rs.getDate("fecha_inicio").toLocalDate(),
						rs.getDate("fecha_fin").toLocalDate(),
						rs.getInt("precio"),
						rs.getDouble("estrellas"),
						rs.getTime("hora_ida").toLocalTime(),
						rs.getTime("hora_vuelta").toLocalTime(),
						rs.getInt("id_avion"),
						rs.getString("imagen")
					);
				}
			}
		}
		return null;
	}

	@Override
	public int saveAndReturnId(Vuelo vuelo) throws Exception {
		int vueloId = -1;

		try (PreparedStatement st = conexion.dameConnection().prepareStatement(QUERY_INSERT, Statement.RETURN_GENERATED_KEYS)) {
			st.setString(1, vuelo.getNombre());
			st.setInt(2, vuelo.getDestino().getId());
			st.setDate(3, Date.valueOf(vuelo.getFecha_inicio()));
			st.setDate(4, Date.valueOf(vuelo.getFecha_fin()));
			st.setInt(5, vuelo.getPrecio());
			st.setDouble(6, vuelo.getEstrellas());
			st.setTime(7, Time.valueOf(vuelo.getHora_ida()));
			st.setTime(8, Time.valueOf(vuelo.getHora_vuelta()));
			st.setInt(9, vuelo.getId_avion());
			st.setString(10, vuelo.getImagen());

			st.executeUpdate();

			try (ResultSet rs = st.getGeneratedKeys()) {
				if (rs.next()) {
					vueloId = rs.getInt(1);
					generarButacasParaVuelo(vueloId, vuelo.getId_avion());
				}
			}
		}

		return vueloId;
	}

	private void generarButacasParaVuelo(int idVuelo, int idAvion) throws Exception {
		String queryCapacidad = "SELECT capacidad FROM avion WHERE id = ?";
		String queryInsertButaca = "INSERT INTO butaca_vuelo (vuelo_id, asiento, estado) VALUES (?, ?, 'disponible')";

		try (PreparedStatement stCapacidad = conexion.dameConnection().prepareStatement(queryCapacidad)) {
			stCapacidad.setInt(1, idAvion);
			try (ResultSet rs = stCapacidad.executeQuery()) {
				if (rs.next()) {
					int capacidad = rs.getInt("capacidad");

					try (PreparedStatement stInsert = conexion.dameConnection().prepareStatement(queryInsertButaca)) {
						for (int i = 1; i <= capacidad; i++) {
							stInsert.setInt(1, idVuelo);
							stInsert.setInt(2, i);
							stInsert.addBatch();
						}
						stInsert.executeBatch();
					}
				} else {
					throw new Exception("Avión no encontrado con id=" + idAvion);
				}
			}
		}
	}

	@Override
	public void updateImage(int id, String imagen) throws Exception {
		try (PreparedStatement st = conexion.dameConnection().prepareStatement(QUERY_UPDATE_IMG)) {
			st.setString(1, imagen);
			st.setInt(2, id);
			st.executeUpdate();
		}
	}

	@Override
	public void delete(int id) throws Exception {
		try (PreparedStatement st = conexion.dameConnection().prepareStatement(QUERY_DELETE)) {
			st.setInt(1, id);
			st.executeUpdate();
		}
	}
}