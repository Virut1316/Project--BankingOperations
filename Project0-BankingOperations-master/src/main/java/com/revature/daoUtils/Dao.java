package com.revature.daoUtils;

import java.util.List;

public interface Dao<T> {
	
	List<T> getAllElements();

	T getElementById(int id);
	T getElementByUsername(String username);
	boolean insertElement(T element);
	boolean deleteElement(int id);
	boolean updateElement(T element);
}
