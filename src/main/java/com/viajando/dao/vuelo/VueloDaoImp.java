package com.viajando.dao.vuelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import com.viajando.config.Conexion;
import com.viajando.domain.Vuelo;
import com.viajando.exception.ErrorException;

public class VueloDaoImp implements VueloDao{
	
	  private Conexion conexion = Conexion.getInstance();

	    private static final String queryListarVuelo = "SELECT * FROM vuelo";
	    private static final String queryBuscarId = "SELECT * FROM vuelo WHERE id = ?";
	    private static final String queryGuardarVuelo = "INSERT INTO vuelo (destino, ida, vuelta, precio, estrellas, hora_ida, hora_vuelta) VALUES (?, ?, ?, ?, ?, ?, ?)";
	    private static final String queryEliminarVuelo = "DELETE FROM vuelo WHERE id = ?";

	    @Override
	    public List<Vuelo> listar() throws Exception {
	        List<Vuelo> vuelos = new ArrayList<>();
	        PreparedStatement st = null;
	        ResultSet rs = null;

	        Connection conn = conexion.dameConnection();
	        if (conn == null) {
	            throw new ErrorException("No se pudo obtener conexión a la base de datos");
	        }

	        try {
	            st = conn.prepareStatement(queryListarVuelo);
	            rs = st.executeQuery();

	            while (rs.next()) {
	                Vuelo vuelo = new Vuelo(0, null, null, null, 0, 0, null, null);
	                vuelo.setId(rs.getInt("id"));
	                vuelo.setDestino(rs.getString("destino"));
	                vuelo.setIda(rs.getDate("ida").toLocalDate());
	                vuelo.setVuelta(rs.getDate("vuelta").toLocalDate());
	                vuelo.setPrecio(rs.getInt("precio"));
	                vuelo.setEstrellas(rs.getDouble("estrellas"));
	                vuelo.setHoraIda(rs.getTime("hora_ida").toLocalTime());
	                vuelo.setHoraVuelta(rs.getTime("hora_vuelta").toLocalTime());

	                vuelos.add(vuelo);
	            }
	        } catch (Exception e) {
	            throw new ErrorException("Error al listar vuelos", e);
	        } finally {
	            if (rs != null) rs.close();
	            if (st != null) st.close();
	        }
	        return vuelos;
	    }

	    @Override
	    public Vuelo buscarPorId(int id) throws Exception {
	        PreparedStatement st = null;
	        ResultSet rs = null;

	        Connection conn = conexion.dameConnection();
	        if (conn == null) {
	            throw new ErrorException("No se pudo obtener conexión a la base de datos");
	        }

	        try {
	            st = conn.prepareStatement(queryBuscarId);
	            st.setInt(1, id);
	            rs = st.executeQuery();

	            if (rs.next()) {
	                return new Vuelo(
	                    rs.getInt("id"),
	                    rs.getString("destino"),
	                    rs.getDate("ida").toLocalDate(),
	                    rs.getDate("vuelta").toLocalDate(),
	                    rs.getInt("precio"),
	                    rs.getDouble("estrellas"),
	                    rs.getTime("hora_ida").toLocalTime(),
	                    rs.getTime("hora_vuelta").toLocalTime()
	                );
	            }
	        } catch (Exception e) {
	            throw new ErrorException("Error al buscar vuelo por ID", e);
	        } finally {
	            if (rs != null) rs.close();
	            if (st != null) st.close();
	        }
	        return null;
	    }

	    @Override
	    public void guardar(String destino, LocalDate ida, LocalDate vuelta, int precio, double estrellas, LocalTime horaIda, LocalTime horaVuelta) throws Exception {
	    	
	    	PreparedStatement st = null;
	        Connection conn = conexion.dameConnection();
	        if (conn == null) {
	            throw new ErrorException("No se pudo obtener conexión a la base de datos");
	        }

	        try {
	            System.out.println("Conexión autocommit? " + conn.getAutoCommit());

	            st = conn.prepareStatement(queryGuardarVuelo);
	            st.setString(1, destino);
	            st.setDate(2, java.sql.Date.valueOf(ida));
	            st.setDate(3, java.sql.Date.valueOf(vuelta));
	            st.setInt(4, precio);
	            st.setDouble(5, estrellas);
	            st.setTime(6, java.sql.Time.valueOf(horaIda));
	            st.setTime(7, java.sql.Time.valueOf(horaVuelta));

	            System.out.println("Ejecutando insert...");
	            int filas = st.executeUpdate();
	            System.out.println("Insert ejecutado. Filas afectadas: " + filas);

	            if (!conn.getAutoCommit()) {
	                conn.commit();
	                System.out.println("Commit realizado.");
	            }
	        } catch (Exception e) {
	            System.err.println("Error en guardar vuelo: " + e.getMessage());
	            e.printStackTrace();
	            throw new ErrorException("Error al guardar vuelo", e);
	        } finally {
	            if (st != null) st.close();
	        }
	    }

	    @Override
	    public void eliminar(int id) throws Exception {
	        PreparedStatement st = null;

	        Connection conn = conexion.dameConnection();
	        if (conn == null) {
	            throw new ErrorException("No se pudo obtener conexión a la base de datos");
	        }

	        try {
	            st = conn.prepareStatement(queryEliminarVuelo);
	            st.setInt(1, id);
	            int affectedRows = st.executeUpdate();

	            if (affectedRows == 0) {
	                throw new ErrorException("No se encontró el vuelo con id " + id);
	            }
	        } catch (Exception e) {
	            throw new ErrorException("Error al eliminar vuelo", e);
	        } finally {
	            if (st != null) st.close();
	        }
	    }
	

}
