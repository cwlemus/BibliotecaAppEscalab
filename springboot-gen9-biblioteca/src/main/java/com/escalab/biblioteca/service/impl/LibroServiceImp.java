package com.escalab.biblioteca.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.escalab.biblioteca.model.Autor;
import com.escalab.biblioteca.model.Libro;
import com.escalab.biblioteca.repository.ILibroRepo;
import com.escalab.biblioteca.service.ILibroService;
@Service
public class LibroServiceImp implements ILibroService {
	
	private final ILibroRepo libroRepo;
	
	public LibroServiceImp(ILibroRepo libroRepo) {
		// TODO Auto-generated constructor stub
		this.libroRepo = libroRepo;
	}

	@Override
	public Libro registrar(Libro obj) {
		// TODO Auto-generated method stub
		return libroRepo.save(obj);
	}

	@Override
	public Libro modificar(Libro obj) {
		Optional<Libro> findLibro = libroRepo.findById(obj.getCodigoLibro());
		if(findLibro.isPresent()) {
			return libroRepo.save(obj);
		}else {
			return new Libro();
		}
	}

	@Override
	public List<Libro> listar() {
		// TODO Auto-generated method stub
		return libroRepo.findAll();
	}

	@Override
	public Libro leerPorId(Integer id) {
		// TODO Auto-generated method stub
		Optional<Libro> findLibro = libroRepo.findById(id);
		if(findLibro.isPresent()) {
			return findLibro.get();
		}else {
			return new Libro();
		}
	}

	@Override
	public boolean eliminar(Integer id) {
		// TODO Auto-generated method stub
		libroRepo.deleteById(id);
		return true;
	}

}
