package com.ventas.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class Accion {

	public abstract String ejecutar(HttpServletRequest request, 
			HttpServletResponse response);
}
