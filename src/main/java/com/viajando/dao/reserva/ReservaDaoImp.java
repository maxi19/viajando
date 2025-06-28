package com.viajando.dao.reserva;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.viajando.config.Conexion;
import com.viajando.domain.Reserva;
import com.viajando.service.reserva.ReservaService;

public class ReservaDaoImp implements ReservaDao {

    private Conexion conexion = Conexion.getInstance();

    private static final String queryList = "SELECT id, identificador, nombre, apellido, sexo, DNI, tipo_servicio, id_vuelo, id_hotel, id_excursion, id_paquete, precio FROM reservas";

    private static final String queryAddReserva = """
    	    INSERT INTO reservas (
    	        identificador, nombre, apellido, sexo, DNI,
    	        tipo_servicio, id_vuelo, id_hotel, id_excursion, id_paquete, precio, butaca
    	    ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
    	    """;

    @Override
    public List<Reserva> list() throws Exception {
        List<Reserva> reservas = new ArrayList<>();
        try (PreparedStatement st = conexion.dameConnection().prepareStatement(queryList);
             ResultSet rs = st.executeQuery()) {
            while (rs.next()) {
                Reserva r = new Reserva(
                    rs.getInt("id"),
                    rs.getString("identificador"),
                    rs.getString("nombre"),
                    rs.getString("apellido"),
                    rs.getString("sexo"),
                    rs.getInt("DNI"),
                    rs.getString("tipo_servicio"),
                    rs.getInt("id_vuelo"),
                    rs.getInt("id_hotel"),
                    rs.getInt("id_excursion"),
                    rs.getInt("id_paquete"),
                    rs.getInt("precio")
                );
                reservas.add(r);
            }
        } catch (SQLException e) {
            throw new Exception("Error al listar reservas", e);
        }
        return reservas;
    }

    @Override
    public void save(Reserva r) throws Exception {
        try (PreparedStatement st = conexion.dameConnection().prepareStatement(queryAddReserva)) {
            st.setString(1, r.getIdentificador());
            st.setString(2, r.getNombre());
            st.setString(3, r.getApellido());
            st.setString(4, r.getSexo());
            st.setInt(5, r.getDni());
            st.setString(6, r.getTipoServicio());
            st.setObject(7, r.getIdVuelo() != 0 ? r.getIdVuelo() : null);
            st.setObject(8, r.getIdHotel() != 0 ? r.getIdHotel() : null);
            st.setObject(9, r.getIdExcursion() != 0 ? r.getIdExcursion() : null);
            st.setObject(10, r.getIdPaquete() != 0 ? r.getIdPaquete() : null);
            st.setInt(11, r.getPrecio());
            st.setString(12, r.getButaca()); 

            int result = st.executeUpdate();
            if (result == 0) {
                throw new Exception("No se pudo guardar la reserva");
            }
        } catch (SQLException e) {
            throw new Exception("Error al guardar reserva", e);
        }
    }

	@Override
	public void save(String identificador, String nombre, String apellido, String sexo, int DNI, String tipo_servicio,
			int id_vuelo, int id_hotel, int id_excursion, int id_paquete, int precio) throws Exception {
		// TODO Auto-generated method stub
		
	}
}
