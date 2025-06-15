package com.viajando.service;

import com.viajando.dao.LoginDao;
import com.viajando.dao.LoginDaolmp;

public class LoginServicelmp implements LoginService{

	private LoginDao loginDao = new LoginDaolmp();
	
	
	public void consultarUsuario(String usuario) throws Exception {
		loginDao.existeUsuario(usuario);
	}


	public void consultarUsuarioYpassword(String usuario, String password) throws Exception {
		loginDao.existeUsuarioPassword(usuario, password);
	}





}
