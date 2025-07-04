package com.viajando.dao.avion;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.viajando.config.Conexion;
import com.viajando.domain.Avion;
import com.viajando.domain.Destino;
import com.viajando.exception.ErrorException;

public class AvionDaoImp implements AvionDao <Integer, Avion>{
	
	private Conexion conexion = Conexion.getInstance();
	
	  private static final String queryList = "SELECT id, nombre, empresa_id, capacidad FROM avion";
	  private static final String queryGetOne = "SELECT id, nombre, empresa_id, capacidad FROM avion WHERE id = ?";
	  
	  

	@Override
	public void add(Avion t) throws ErrorException {
		// TODO Auto-generated method stub
		
	}




	@Override
	public void delete(Integer i) throws ErrorException {
		// TODO Auto-generated method stub
		
	}




	@Override
	public void edit(Avion t) throws ErrorException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List <Avion> list() throws ErrorException {
	    ResultSet rs = null;
        List<Avion> aviones = null;
        PreparedStatement st = null;

        try {
            st = conexion.dameConnection().prepareStatement(queryList);
            rs = st.executeQuery();
            aviones = new ArrayList<>();

            while (rs.next()) {
                Avion avion = new Avion(
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getInt("empresa_id"),
                    rs.getInt("capacidad")
                );
                aviones.add(avion);
            }

        } catch (Exception e) {
            throw new ErrorException("Hubo un error al listar los aviones", e);
        } finally {
            try {
                if (st != null) st.close();
                if (rs != null) rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return aviones;
	}


    @Override
	public Avion getOne(Integer id) throws ErrorException {
    	  PreparedStatement st = null;
          ResultSet rs = null;
          Avion avion = null;

          try {
              st = conexion.dameConnection().prepareStatement(queryGetOne);
              st.setInt(1, id);
              rs = st.executeQuery();

              if (rs.next()) {
                  avion = new Avion(
                      rs.getInt("id"),
                      rs.getString("nombre"),
                      rs.getInt("empresa_id"),
                      rs.getInt("capacidad")
                  );
              }

          } catch (Exception e) {
              throw new ErrorException("Error al obtener el avión", e);
          } finally {
              try {
                  if (rs != null) rs.close();
                  if (st != null) st.close();
              } catch (SQLException e) {
                  e.printStackTrace();
              }
          }

          return avion;
      
	}




}
