package com.viajando.dao.paquete;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.viajando.config.Conexion;
import com.viajando.dao.excursion.ExcursionDao;
import com.viajando.dao.excursion.ExcursionDaoImp;
import com.viajando.dao.hotel.HotelDao;
import com.viajando.dao.hotel.HotelDaoImp;
import com.viajando.dao.vuelo.VueloDao;
import com.viajando.dao.vuelo.VueloDaoImp;
import com.viajando.domain.Hotel;
import com.viajando.domain.Excursion;
import com.viajando.domain.Vuelo;
import com.viajando.domain.Paquete;
import com.viajando.exception.ErrorException;

public class PaqueteDaoImp implements PaqueteDao {

	private Conexion conexion = Conexion.getInstance();
	private ExcursionDao excursionDao = new ExcursionDaoImp();
	private VueloDao vueloDao = new VueloDaoImp();
	private HotelDao hotelDao = new HotelDaoImp();

	private static final String queryConsultarPaquete = "SELECT id, nombre, descripcion, hotel_id, vuelo_id, excursion_id, estrellas, personas, precio FROM paquete WHERE id=?";

	private static final String queryAddPaquete = "INSERT INTO paquete (nombre, descripcion, hotel_id, vuelo_id, excursion_id, estrellas, personas, precio) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

	private static final String queryUpdateImage = "UPDATE paquete SET imagen=? WHERE id=?";

	private static final String queryDeletePaquete = "DELETE FROM paquete WHERE id=?";

	private static final String queryList = "SELECT id, nombre, descripcion, hotel_id, vuelo_id, excursion_id, estrellas, personas, precio FROM paquete";

	@Override
	public List<Paquete> list() throws Exception {
		ResultSet rs = null;
		PreparedStatement st = null;
		List<Paquete> paquetes = new ArrayList<>();

		try {
			st = conexion.dameConnection().prepareStatement(queryList);
			rs = st.executeQuery();

			while (rs.next()) {
				int hotelId = rs.getInt("hotel_id");
				Hotel hotel = hotelDao.findById(hotelId);
				if (!rs.wasNull()) {
				    hotel = hotelDao.findById(hotelId);
				}
				
				int vueloId = rs.getInt("vuelo_id");
				Vuelo vuelo = null;
				if (!rs.wasNull()) {
				    vuelo = vueloDao.findById(vueloId);
				}

				int excursionId = rs.getInt("excursion_id");
				Excursion excursion = null;
				if (!rs.wasNull()) {
				    excursion = excursionDao.findById(excursionId);
				}

				paquetes.add(new Paquete(rs.getInt("id"), rs.getString("nombre"), rs.getString("descripcion"), hotel,
						vuelo, excursion, rs.getDouble("estrellas"), rs.getInt("personas"), rs.getInt("precio")));
			}

		} finally {
			if (st != null)
				st.close();
			if (rs != null)
				rs.close();
		}

		return paquetes;
	}

	@Override
	public Paquete findById(int id) throws Exception {
		ResultSet rs = null;
		PreparedStatement st = null;

		try {
			st = conexion.dameConnection().prepareStatement(queryConsultarPaquete);
			st.setInt(1, id);
			rs = st.executeQuery();

			while (rs.next()) {
	            // HOTEL
	            int hotelId = rs.getInt("hotel_id");
	            Hotel hotel = null;
	            if (!rs.wasNull()) {
	                hotel = hotelDao.findById(hotelId);
	            }

	            // VUELO
	            int vueloId = rs.getInt("vuelo_id");
	            Vuelo vuelo = null;
	            if (!rs.wasNull()) {
	                vuelo = vueloDao.findById(vueloId);
	            }

	            // EXCURSION
	            int excursionId = rs.getInt("excursion_id");
	            Excursion excursion = null;
	            if (!rs.wasNull()) {
	                excursion = excursionDao.findById(excursionId);
	            }
				return new Paquete(rs.getInt("id"), rs.getString("nombre"), rs.getString("descripcion"), hotel, vuelo,
						excursion, rs.getDouble("estrellas"), rs.getInt("personas"), rs.getInt("precio"));
			}

		} catch (Exception e) {
			throw new ErrorException("Hubo un error al realizar la consulta", e);
		} finally {
			if (st != null)
				st.close();
			if (rs != null)
				rs.close();
		}

		return null;
	}

	@Override
	public int saveAndReturnId(String nombre, String descripcion, int hotel_id, int vuelo_id, int excursion_id,
			double estrellas, int personas, int precio) throws Exception {
		PreparedStatement st = null;
		ResultSet rs = null;
		int idGenerado = -1;

		try {
			st = conexion.dameConnection().prepareStatement(queryAddPaquete, Statement.RETURN_GENERATED_KEYS);
			st.setString(1, nombre);
			st.setString(2, descripcion);

			if (hotel_id == 0) {
				st.setNull(3, java.sql.Types.INTEGER);
			} else {
				st.setInt(3, hotel_id);
			}

			if (vuelo_id == 0) {
				st.setNull(4, java.sql.Types.INTEGER);
			} else {
				st.setInt(4, vuelo_id);
			}

			if (excursion_id == 0) {
				st.setNull(5, java.sql.Types.INTEGER);
			} else {
				st.setInt(5, excursion_id);
			}

			st.setDouble(6, estrellas);
			st.setInt(7, personas);
			st.setInt(8, precio);

			st.executeUpdate();

			rs = st.getGeneratedKeys();
			if (rs.next()) {
				idGenerado = rs.getInt(1);
			}
		} finally {
			if (rs != null)
				rs.close();
			if (st != null)
				st.close();
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
			if (st != null)
				st.close();
		}
	}

	@Override
	public void delete(int id) throws Exception {
		PreparedStatement st = null;
		try {
			st = conexion.dameConnection().prepareStatement(queryDeletePaquete);
			st.setInt(1, id);
			int rowsAffected = st.executeUpdate();

			if (rowsAffected == 0) {
				throw new Error("No se encontró el registro");
			}
		} catch (Exception e) {
			throw new ErrorException("Hubo un error al realizar la consulta", e);
		} finally {
			if (st != null)
				st.close();
		}
	}
}
