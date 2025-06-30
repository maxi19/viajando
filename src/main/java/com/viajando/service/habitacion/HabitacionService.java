package com.viajando.service.habitacion;

import com.viajando.domain.Habitacion;
import java.util.List;

public interface HabitacionService {
    List<Habitacion> getHabitacionesPorHotel(int hotelId) throws Exception;
    boolean estaDisponible(int hotelId, String nombreHabitacion) throws Exception;
    void marcarOcupada(int hotelId, String nombreHabitacion) throws Exception;
    void crearHabitacion(int hotelId, String nombreHabitacion, int cantidad) throws Exception;

}
