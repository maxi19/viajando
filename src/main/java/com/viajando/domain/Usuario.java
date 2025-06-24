package com.viajando.domain;

public class Usuario extends GenericEntity{
	
	private String usuario;
	private String password;
	

	public Usuario(int id, String usuario, String password) {
		super(id);
		this.usuario = usuario;
		this.password = password;
	}



	public String getUsuario() {
		return usuario;
	}



	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
