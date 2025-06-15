package com.viajando.service;


public interface LoginService {
			
	public void consultarUsuario(String usuario) throws Exception;
	
	public void consultarUsuarioYpassword(String usuario, String  password) throws Exception;

	
	
}
