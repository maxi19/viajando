package com.viajando.dao.excursion;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.viajando.config.Conexion;
import com.viajando.domain.Excursion;
import com.viajando.domain.Destino;
import com.viajando.exception.ErrorException;

public class ExcursionDaoImp implements ExcursionDao {

    private Conexion conexion = Conexion.getInstance();

    private static final String queryConsultarExcursion = "SELECT id, nombre, descripcion, fecha_inicio, fecha_fin, precio, destino_id, estrellas, imagen FROM excursion WHERE id=?";
    private static final String queryAddExcursion = "INSERT INTO excursion (nombre, descripcion, fecha_inicio, fecha_fin, precio, destino_id, estrellas) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String queryUpdateImage = "UPDATE excursion SET imagen=? WHERE id=?";
    private static final String queryDeleteExcursion = "DELETE FROM excursion WHERE id=?";

    private static final String queryList = "SELECT e.id AS excursion_id, e.nombre AS excursion_nombre, e.descripcion, e.fecha_inicio, e.fecha_fin, e.precio AS excursion_precio, e.estrellas, e.imagen, d.id AS destino_id, d.nombre AS destino_nombre, d.pais AS destino_pais, d.precio AS destino_precio FROM excursion e  JOIN destinos d ON e.destino_id = d.id";

    @Override
    public List<Excursion> list() throws Exception {
        List<Excursion> excursiones = new ArrayList<>();

        try (PreparedStatement st = conexion.dameConnection().prepareStatement(queryList);
             ResultSet rs = st.executeQuery()) {

            while (rs.next()) {
                Destino destino = new Destino(
                    rs.getInt("destino_id"),
                    rs.getString("destino_nombre"),
                    rs.getString("destino_pais"),
                    rs.getInt("destino_precio")
                );

                Excursion excursion = new Excursion(
                    rs.getInt("excursion_id"),
                    rs.getString("excursion_nombre"),
                    rs.getString("descripcion"),
                    rs.getDate("fecha_inicio").toLocalDate(),
                    rs.getDate("fecha_fin").toLocalDate(),
                    rs.getInt("excursion_precio"),
                    destino,
                    rs.getDouble("estrellas"),
                    rs.getString("imagen")
                );

                excursiones.add(excursion);
            }

        } catch (Exception e) {
            System.out.println("Error en ExcursionDaoImp.list: " + e.getMessage());
            e.printStackTrace();
        }

        return excursiones;
    }

    @Override
    public Excursion findById(int id) throws Exception {
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conexion.dameConnection().prepareStatement(queryConsultarExcursion);
            st.setInt(1, id);
            rs = st.executeQuery();

            if (rs.next()) {
                int destinoId = rs.getInt("destino_id");
                String destinoNombre = ""; // Default
                String destinoPais = "";
                int destinoPrecio = 0;

                // Opción: Hacer otro SELECT con JOIN si necesitás más info del destino
                Destino destino = new Destino(destinoId, destinoNombre, destinoPais, destinoPrecio);

                return new Excursion(
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getString("descripcion"),
                    rs.getDate("fecha_inicio").toLocalDate(),
                    rs.getDate("fecha_fin").toLocalDate(),
                    rs.getInt("precio"),
                    destino,
                    rs.getDouble("estrellas"),
                    rs.getString("imagen")
                );
            }

        } catch (Exception e) {
            throw new ErrorException("Hubo un error al realizar la consulta", e);
        } finally {
            if (rs != null) try { rs.close(); } catch (SQLException e) { e.printStackTrace(); }
            if (st != null) try { st.close(); } catch (SQLException e) { e.printStackTrace(); }
        }

        return null;
    }

    @Override
    public int saveAndReturnId(String nombre, String descripcion, LocalDate fecha_inicio, LocalDate fecha_fin,
                               int precio, int destino_id, double estrellas) throws Exception {
        int idGenerado = -1;
        try (PreparedStatement st = conexion.dameConnection().prepareStatement(queryAddExcursion, Statement.RETURN_GENERATED_KEYS)) {
            st.setString(1, nombre);
            st.setString(2, descripcion);
            st.setDate(3, Date.valueOf(fecha_inicio));
            st.setDate(4, Date.valueOf(fecha_fin));
            st.setInt(5, precio);
            st.setInt(6, destino_id);
            st.setDouble(7, estrellas);

            st.executeUpdate();

            try (ResultSet rs = st.getGeneratedKeys()) {
                if (rs.next()) {
                    idGenerado = rs.getInt(1);
                }
            }
        }

        return idGenerado;
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
        try (PreparedStatement st = conexion.dameConnection().prepareStatement(queryDeleteExcursion)) {
            st.setInt(1, id);
            int rowsAffected = st.executeUpdate();
            if (rowsAffected == 0) {
                throw new Error("No se encontró el registro");
            }
        } catch (Exception e) {
            throw new ErrorException("Hubo un error al realizar la consulta", e);
        }
    }
}
