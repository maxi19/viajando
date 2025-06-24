package com.viajando.dao.excursion;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.viajando.config.Conexion;
import com.viajando.domain.Excursion;
import com.viajando.exception.ErrorException;

public class ExcursionDaoImp implements ExcursionDao {

	private Conexion conexion = Conexion.getInstance();

	private static final String queryConsultarExcursion = "SELECT id, nombre, descripcion, fecha_inicio, fecha_fin, precio, estrellas, imagen FROM excursion where id=?";

	private static final String queryAddExcursion = "INSERT INTO excursion (nombre, descripcion, fecha_inicio, fecha_fin, precio, destino, estrellas) VALUES (?,?,?,?,?,?,?)";

    private static final String queryUpdateImage = "UPDATE excursion SET imagen=? WHERE id=?";

	private static final String queryDeleteExcursion = "DELETE FROM excursion WHERE id=?";

	private static final String queryList = "SELECT id, nombre, descripcion, fecha_inicio, fecha_fin, precio, destino, estrellas, imagen FROM excursion";

	@Override
	public List<Excursion> list() throws Exception {
		ResultSet rs = null;
		PreparedStatement st = null;

		List<Excursion> excursion = new ArrayList<Excursion>();

		try {
			st = conexion.dameConnection().prepareStatement(queryList);
			rs = st.executeQuery();

			while (rs.next()) {
				excursion.add(new Excursion(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDate(4).toLocalDate(),
						rs.getDate(5).toLocalDate(), rs.getInt(6), rs.getString(7), rs.getDouble(8), rs.getString(9)));
			}

		} catch (Exception e) {
			System.out.println(e.getCause());
		} finally {
			st.close();
			rs.close();
		}

		return excursion;
	}

	@Override
	public Excursion findById(int id) throws Exception {
		ResultSet rs = null;
		PreparedStatement st = null;
		try {
			st = conexion.dameConnection().prepareStatement(queryConsultarExcursion);
			st.setInt(1, id);
			rs = st.executeQuery();
			if (rs.next()) {
				return new Excursion(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDate(4).toLocalDate(),
						rs.getDate(5).toLocalDate(), rs.getInt(6), rs.getString(7), 1, "");
			}

		} catch (Exception e) {
			throw new ErrorException("Hubo un error al realizar la consulta", e);
		} finally {
			try {
				st.close();
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return null;
	}

	
    @Override
    public int saveAndReturnId(String nombre, String descripcion, LocalDate fecha_inicio, LocalDate fecha_fin,
                               int precio, String destino, double estrellas) throws Exception {
        PreparedStatement st = null;
        ResultSet rs = null;
        int idGenerado = -1;

        try {
            st = conexion.dameConnection().prepareStatement(queryAddExcursion, Statement.RETURN_GENERATED_KEYS);
            st.setString(1, nombre);
            st.setString(2, descripcion);
            st.setDate(3, java.sql.Date.valueOf(fecha_inicio));
            st.setDate(4, java.sql.Date.valueOf(fecha_fin));
            st.setInt(5, precio);
            st.setString(6, destino);
            st.setDouble(7, estrellas);
            st.executeUpdate();

            rs = st.getGeneratedKeys(); // pide la llave generada automaticamente, osea la primary key
            if (rs.next()) {
                idGenerado = rs.getInt(1); 
            }
        } finally {
            if (st != null) st.close();
            if (rs != null) rs.close();
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
            if (st != null) st.close();
        }
    }
		 
	@Override
	public void delete(int id) throws Exception {
		 ResultSet rs = null;
		 PreparedStatement st = null;
		 try{
			st = conexion.dameConnection().prepareStatement(queryDeleteExcursion);
			st.setInt(1, id);
			int rowsAffected = st.executeUpdate();
			if (rowsAffected == 0) {
			    throw new Error("No se encontró el registro");
			}

		 }catch (Exception e) {
				throw new ErrorException("Hubo un error al realizar la consulta", e);
		}finally {
			try {
				st.close();
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
	}

}



