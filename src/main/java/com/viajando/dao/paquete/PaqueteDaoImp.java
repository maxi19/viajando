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
import com.viajando.domain.Destino;
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
	
	private static final String queryListJoin = "SELECT "
	        + "p.id, p.nombre AS paquete_nombre, p.descripcion AS paquete_descripcion, p.estrellas, p.personas, p.precio, "

	        + "h.id AS hotel_id, h.nombre AS hotel_nombre, h.precio AS hotel_precio, h.estrellas AS hotel_estrellas, h.imagen AS hotel_imagen, "
	        + "dh.id AS hotel_destino_id, dh.nombre AS hotel_destino_nombre, dh.pais AS hotel_destino_pais, "

	        + "v.id AS vuelo_id, v.nombre AS vuelo_nombre, v.precio AS vuelo_precio, v.imagen AS vuelo_imagen, "
	        + "dv.id AS vuelo_destino_id, dv.nombre AS vuelo_destino_nombre, dv.pais AS vuelo_destino_pais, "

	        + "e.id AS excursion_id, e.nombre AS excursion_nombre, e.descripcion AS excursion_descripcion, e.fecha_inicio AS excursion_fecha_inicio, "
	        + "e.fecha_fin AS excursion_fecha_fin, e.precio AS excursion_precio, e.estrellas AS excursion_estrellas, e.imagen AS excursion_imagen, "
	        + "de.id AS excursion_destino_id, de.nombre AS excursion_destino_nombre, de.pais AS excursion_destino_pais "

	        + "FROM paquete p "
	        + "LEFT JOIN hotel h ON p.hotel_id = h.id "
	        + "LEFT JOIN destinos dh ON h.destino_id = dh.id "
	        + "LEFT JOIN vuelo v ON p.vuelo_id = v.id "
	        + "LEFT JOIN destinos dv ON v.destino_id = dv.id "
	        + "LEFT JOIN excursion e ON p.excursion_id = e.id "
	        + "LEFT JOIN destinos de ON e.destino_id = de.id";

	@Override
	public List<Paquete> list() throws Exception {
	    List<Paquete> paquetes = new ArrayList<>();



	    try (PreparedStatement st = conexion.dameConnection().prepareStatement(queryListJoin);
	         ResultSet rs = st.executeQuery()) {

	        while (rs.next()) {

	            // Hotel y Destino del hotel
	            Hotel hotel = null;
	            int hotelId = rs.getInt("hotel_id");
	            if (!rs.wasNull()) {
	                Destino destinoHotel = new Destino(
	                    rs.getInt("hotel_destino_id"),
	                    rs.getString("hotel_destino_nombre"),
	                    rs.getString("hotel_destino_pais"), hotelId
	                );

	                hotel = new Hotel(
	                    hotelId,
	                    rs.getString("hotel_nombre"),
	                    destinoHotel,
	                    rs.getDouble("hotel_estrellas"),
	                    rs.getInt("hotel_precio"),
	                    rs.getString("hotel_imagen"),
	                    0 // stock no necesario si no se usa en vista
	                );
	            }

	            // Vuelo y Destino del vuelo
	            Vuelo vuelo = null;
	            int vueloId = rs.getInt("vuelo_id");
	            if (!rs.wasNull()) {
	                Destino destinoVuelo = new Destino(
	                    rs.getInt("vuelo_destino_id"),
	                    rs.getString("vuelo_destino_nombre"),
	                    rs.getString("vuelo_destino_pais"), vueloId
	                );

	                vuelo = new Vuelo(
	                    vueloId,
	                    rs.getString("vuelo_nombre"),
	                    destinoVuelo,
	                    null, // fecha_inicio
	                    null, // fecha_fin
	                    rs.getInt("vuelo_precio"),
	                    0, // estrellas no traídas
	                    null, // hora_ida
	                    null, // hora_vuelta
	                    0, // id_avion
	                    rs.getString("vuelo_imagen")
	                );
	            }

	            // Excursión y su destino
	            Excursion excursion = null;
	            int excursionId = rs.getInt("excursion_id");
	            if (!rs.wasNull()) {
	                Destino destinoExcursion = new Destino(
	                    rs.getInt("excursion_destino_id"),
	                    rs.getString("excursion_destino_nombre"),
	                    rs.getString("excursion_destino_pais"), excursionId
	                );

	                excursion = new Excursion(
	                    excursionId,
	                    rs.getString("excursion_nombre"),
	                    rs.getString("excursion_descripcion"),
	                    rs.getDate("excursion_fecha_inicio").toLocalDate(),
	                    rs.getDate("excursion_fecha_fin").toLocalDate(),
	                    rs.getInt("excursion_precio"),
	                    destinoExcursion,
	                    rs.getDouble("excursion_estrellas"),
	                    rs.getString("excursion_imagen")
	                );
	            }

	            // Armar paquete
	            Paquete paquete = new Paquete(
	                rs.getInt("id"),
	                rs.getString("paquete_nombre"),
	                rs.getString("paquete_descripcion"),
	                hotel,
	                vuelo,
	                excursion,
	                rs.getDouble("estrellas"),
	                rs.getInt("personas"),
	                rs.getInt("precio")
	            );

	            paquetes.add(paquete);
	        }

	    } catch (Exception e) {
	        System.err.println("Error en PaqueteDaoImp.list: " + e.getMessage());
	        e.printStackTrace();
	        throw e;
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

	
	@Override
	public int calcularPrecio(int hotel_id, int vuelo_id, int excursion_id, int personas) throws Exception {
		    int precioHotel = 0;
		    int precioVuelo = 0;
		    int precioExcursion = 0;
		    
		    if (hotel_id != 0) {
		        Hotel hotel = hotelDao.findById(hotel_id);
		        if (hotel != null) {
		            precioHotel = hotel.getPrecio();
		        }
		    }

		    if (vuelo_id != 0) {
		        Vuelo vuelo = vueloDao.findById(vuelo_id);
		        if (vuelo != null) {
		            precioVuelo = vuelo.getPrecio();
		        }
		    }

		    if (excursion_id != 0) {
		        Excursion excursion = excursionDao.findById(excursion_id);
		        if (excursion != null) {
		            precioExcursion = excursion.getPrecio();
		        }
		    }

		    int precioPorPersona = precioHotel + precioVuelo + precioExcursion;
		    return precioPorPersona * personas;
		}
	
	@Override
	public int saveAndReturnIdSimple(String nombre, String descripcion, int hotel_id, int vuelo_id, int excursion_id, int precio, int personas)
			throws Exception {
		    PreparedStatement st = null;
		    ResultSet rs = null;
		    int idGenerado = -1;

		    try {
		        // Calcula el precio del paquete
		        int precioPaquete = calcularPrecio(hotel_id, vuelo_id, excursion_id, personas);

		        // Prepara el insert
		        st = conexion.dameConnection().prepareStatement(queryAddPaquete, Statement.RETURN_GENERATED_KEYS);
		        
		       
		        st.setString(1, "nombre");
		        st.setString(2, "descripcion");

		        // Hotel
		        if (hotel_id == 0) {
		            st.setNull(3, java.sql.Types.INTEGER);
		        } else {
		            st.setInt(3, hotel_id);
		        }

		        // Vuelo
		        if (vuelo_id == 0) {
		            st.setNull(4, java.sql.Types.INTEGER);
		        } else {
		            st.setInt(4, vuelo_id);
		        }

		        // Excursión
		        if (excursion_id == 0) {
		            st.setNull(5, java.sql.Types.INTEGER);
		        } else {
		            st.setInt(5, excursion_id);
		        }

		        // Estrellas (0 por defecto)
		        st.setDouble(6, 0.0);
		        st.setInt(7, personas);

		        // Precio calculado
		        st.setInt(8, precio);

		        st.executeUpdate();

		        rs = st.getGeneratedKeys();
		        if (rs.next()) {
		            idGenerado = rs.getInt(1);
		        }

		    } finally {
		        if (rs != null) rs.close();
		        if (st != null) st.close();
		    }

		    return idGenerado;
		}

	@Override
	public double calcularEstrellas(int hotel_id, int vuelo_id, int excursion_id) throws Exception {
		   double EstrellasHotel = 0.0;
		   double EstrellasVuelo = 0.0;
		   double EstrellaExcursion = 0.0;
		   double sumaEstrellasPaquete = 0.0;
		   int cantidad = 0; // llevar la cuenta de cuántos componentes tienen estrellas válidas, es decir, que realmente fueron cargados en el paquete


		    
		    if (hotel_id != 0) {
		        Hotel hotel = hotelDao.findById(hotel_id);
		        if (hotel != null) {
		        	EstrellasHotel = hotel.getEstrellas();
		            cantidad++;
		        }
		    }

		    if (vuelo_id != 0) {
		        Vuelo vuelo = vueloDao.findById(vuelo_id);
		        if (vuelo != null) {
		        	EstrellasVuelo = vuelo.getEstrellas();
		            cantidad++;
		        }
		    }

		    if (excursion_id != 0) {
		        Excursion excursion = excursionDao.findById(excursion_id);
		        if (excursion != null) {
		        	EstrellaExcursion = excursion.getEstrellas();
		            cantidad++;
		        }
		    }

		    sumaEstrellasPaquete = EstrellasHotel + EstrellasVuelo + EstrellaExcursion;
		    double PromedioEstrellas = sumaEstrellasPaquete/cantidad;
		    return Math.min(PromedioEstrellas, 5.0); // por si acaso alguna suma da más de 5
	}
}
