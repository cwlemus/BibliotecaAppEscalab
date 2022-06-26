package com.escalab.biblioteca.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.escalab.biblioteca.model.PrestamoLibro;
import com.escalab.biblioteca.repository.IPrestamoLibroRepo;
import com.escalab.biblioteca.service.IPrestamoLibroService;
@Service
public class PrestamoLibroServiceImpl implements IPrestamoLibroService {

	private  IPrestamoLibroRepo prestamoRepo;
	
	@Autowired
	public PrestamoLibroServiceImpl(IPrestamoLibroRepo prestamoRepo) {
		// TODO Auto-generated constructor stub
		this.prestamoRepo=prestamoRepo;
	}
	
	
	@Override
	@Transactional
	public PrestamoLibro registrar(PrestamoLibro obj) {
		// TODO Auto-generated method stub
		return prestamoRepo.save(obj);
	}

	@Override
	public PrestamoLibro modificar(PrestamoLibro obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PrestamoLibro> listar() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PrestamoLibro leerPorId(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean eliminar(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public PrestamoLibro librosPrestadosByEstudiante(Integer codigoEstudiante) {
		// TODO Auto-generated method stub
		return prestamoRepo.librosPrestadosByEstudiante(codigoEstudiante);
	}

	

}
