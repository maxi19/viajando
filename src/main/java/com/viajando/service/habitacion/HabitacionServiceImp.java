package com.viajando.service.habitacion;

import com.viajando.dao.habitacion.HabitacionDao;
import com.viajando.dao.habitacion.HabitacionDaoImp;
import com.viajando.domain.Habitacion;
import java.util.List;

public class HabitacionServiceImp implements HabitacionService {

    private HabitacionDao dao = new HabitacionDaoImp();

    @Override
    public List<Habitacion> getHabitacionesPorHotel(int hotelId) throws Exception {
        return dao.getHabitacionesPorHotel(hotelId);
    }

    @Override
    public boolean estaDisponible(int hotelId, String nombreHabitacion) throws Exception {
        return dao.estaDisponible(hotelId, nombreHabitacion);
    }

    @Override
    public void marcarOcupada(int hotelId, String nombreHabitacion) throws Exception {
        dao.marcarOcupada(hotelId, nombreHabitacion);
    }
    
    @Override
    public void crearHabitacion(int hotelId, String nombreHabitacion, int cantidad) throws Exception {
        dao.crearHabitacion(hotelId, nombreHabitacion, cantidad);
    }
}
