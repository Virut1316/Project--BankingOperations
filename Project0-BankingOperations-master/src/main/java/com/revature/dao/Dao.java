package com.revature.dao;

import java.util.List;

public interface Dao<T> {
	
	List<T> getAllElements();

	T getElementById(int id);
	T getElementByUsername(String username);
	void insertElement(T element);
	void deleteElement(T element);
	void updateElement(T element);
}
