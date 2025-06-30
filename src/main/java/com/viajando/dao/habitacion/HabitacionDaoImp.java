package com.viajando.dao.habitacion;

import com.viajando.config.Conexion;
import com.viajando.domain.Habitacion;
import com.viajando.exception.ErrorException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HabitacionDaoImp implements HabitacionDao {

    private Conexion conexion = Conexion.getInstance();

    private static final String QUERY_LISTAR_POR_HOTEL = "SELECT * FROM habitacion WHERE hotel_id = ?";
    private static final String QUERY_DISPONIBLE = "SELECT estado FROM habitacion WHERE hotel_id = ? AND habitacion = ?";
    private static final String QUERY_OCUPAR = "UPDATE habitacion SET estado = 'ocupado' WHERE hotel_id = ? AND habitacion = ?";
    private static final String QUERY_INSERTAR = "INSERT INTO habitacion (hotel_id, habitacion, cantidad, estado) VALUES (?, ?, ?, 'disponible')";

    @Override
    public List<Habitacion> getHabitacionesPorHotel(int hotelId) throws Exception {
        PreparedStatement st = null;
        ResultSet rs = null;
        List<Habitacion> lista = new ArrayList<>();

        try {
            st = conexion.dameConnection().prepareStatement(QUERY_LISTAR_POR_HOTEL);
            st.setInt(1, hotelId);
            rs = st.executeQuery();

            while (rs.next()) {
                Habitacion h = new Habitacion();
                h.setId(rs.getInt("id"));
                h.setHotelId(rs.getInt("hotel_id"));
                h.setHabitacion(rs.getString("habitacion"));
                h.setCantidad(rs.getInt("cantidad"));
                h.setEstado(rs.getString("estado"));
                lista.add(h);
            }
        } catch (Exception e) {
            throw new ErrorException("Error al consultar habitaciones por hotel", e);
        } finally {
            if (rs != null) rs.close();
            if (st != null) st.close();
        }
        return lista;
    }

    @Override
    public boolean estaDisponible(int hotelId, String nombreHabitacion) throws Exception {
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conexion.dameConnection().prepareStatement(QUERY_DISPONIBLE);
            st.setInt(1, hotelId);
            st.setString(2, nombreHabitacion);
            rs = st.executeQuery();

            if (rs.next()) {
                return rs.getString("estado").equalsIgnoreCase("disponible");
            }
            return false;
        } catch (Exception e) {
            throw new ErrorException("Error al verificar disponibilidad de la habitación", e);
        } finally {
            if (rs != null) rs.close();
            if (st != null) st.close();
        }
    }

    @Override
    public void marcarOcupada(int hotelId, String nombreHabitacion) throws Exception {
        PreparedStatement st = null;

        try {
            st = conexion.dameConnection().prepareStatement(QUERY_OCUPAR);
            st.setInt(1, hotelId);
            st.setString(2, nombreHabitacion);
            st.executeUpdate();
        } catch (Exception e) {
            throw new ErrorException("Error al marcar habitación como ocupada", e);
        } finally {
            if (st != null) st.close();
        }
    }
    

@Override
public void crearHabitacion(int hotelId, String nombreHabitacion, int cantidad) throws Exception {
    PreparedStatement st = null;

    try {
        st = conexion.dameConnection().prepareStatement(QUERY_INSERTAR);
        st.setInt(1, hotelId);
        st.setString(2, nombreHabitacion);
        st.setInt(3, cantidad);
        st.executeUpdate();
    } catch (Exception e) {
        throw new ErrorException("Error al crear habitación", e);
    } finally {
        if (st != null) st.close();
    }
}
    
}
