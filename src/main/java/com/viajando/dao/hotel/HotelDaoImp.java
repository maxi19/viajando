package com.viajando.dao.hotel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
	private static final String queryInsertHabitacion = "INSERT INTO habitacion (hotel_id, habitacion, cantidad, estado) VALUES (?, ?, ?, 'disponible')";

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
			throw new ErrorException("Error al listar hoteles", e);
		}

		return hoteles;
	}

	@Override
	public Hotel findById(int id) throws Exception {
		try (PreparedStatement st = conexion.dameConnection().prepareStatement(queryConsultarHotel)) {
			st.setInt(1, id);
			try (ResultSet rs = st.executeQuery()) {
				if (rs.next()) {
					Destino destino = destinoDao.getOne(rs.getInt("destino_id"));
					return new Hotel(
						rs.getInt("id"),
						rs.getString("nombre"),
						destino,
						rs.getDouble("estrellas"),
						rs.getInt("precio"),
						rs.getString("imagen"),
						rs.getInt("stock")
					);
				}
			}
		}
		return null;
	}

	@Override
	public int saveAndReturnId(Hotel hotel) throws Exception {
		int idGenerado = -1;

		try (PreparedStatement st = conexion.dameConnection().prepareStatement(queryAddHotel, Statement.RETURN_GENERATED_KEYS)) {
			st.setString(1, hotel.getNombre());
			st.setInt(2, hotel.getDestino().getId());
			st.setDouble(3, hotel.getEstrellas());
			st.setInt(4, hotel.getPrecio());
			st.setInt(5, hotel.getStock());
			st.executeUpdate();

			try (ResultSet rs = st.getGeneratedKeys()) {
				if (rs.next()) {
					idGenerado = rs.getInt(1);
					generarHabitacionesParaHotel(idGenerado, hotel);
				}
			}
		}
		return idGenerado;
	}

	private void generarHabitacionesParaHotel(int hotelId, Hotel hotel) throws Exception {
		try (PreparedStatement st = conexion.dameConnection().prepareStatement(queryInsertHabitacion)) {
			for (int i = 0; i < hotel.getStock(); i++) {
				String tipo = hotel.getTiposHabitacion().get(i);
				int capacidad = hotel.getCapacidades().get(i);

				if (tipo == null || tipo.isEmpty()) tipo = "Habitación " + (i + 1);

				st.setInt(1, hotelId);
				st.setString(2, tipo);
				st.setInt(3, capacidad);
				st.addBatch();
			}
			st.executeBatch();
		}
	}

	@Override
	public void updateImage(int id, String nombreImagen) throws Exception {
		try (PreparedStatement st = conexion.dameConnection().prepareStatement(queryUpdateImage)) {
			st.setString(1, nombreImagen);
			st.setInt(2, id);
			st.executeUpdate();
		}
	}

	@Override
	public void delete(int id) throws Exception {
		try (PreparedStatement st = conexion.dameConnection().prepareStatement(queryDeleteHotel)) {
			st.setInt(1, id);
			st.executeUpdate();
		}
	}

	@Override
	public void crearHabitaciones(int hotelId, int stock, HttpServletRequest req) throws Exception {
		// TODO Auto-generated method stub
		
	}
}
