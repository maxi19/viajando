package com.viajando.dao;

import java.util.List;

import com.viajando.exception.ErrorException;

public interface DaoBase<I,T> {

	public void add(T t) throws ErrorException;
	
	public void delete(I i) throws ErrorException;

	public void edit(T t) throws ErrorException;

	public List<T> list() throws ErrorException;
	
	public T getOne(I i) throws ErrorException;
}
