package com.escalab.biblioteca.service;

import java.util.List;

import com.escalab.biblioteca.model.Menu;

public interface IMenuService extends ICRUD<Menu> {
	
	List<Menu> listarMenuPorUsuario(String nombre);

}
