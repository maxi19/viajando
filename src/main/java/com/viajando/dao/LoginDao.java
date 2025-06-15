package com.viajando.dao;


public interface LoginDao {
	
	public void existeUsuario(String usuario) throws Exception ;
	
	public void existeUsuarioPassword(String usuario, String password) throws Exception ;
	
	public void agregarReintento(String usuario) throws Exception;
	
	
}
