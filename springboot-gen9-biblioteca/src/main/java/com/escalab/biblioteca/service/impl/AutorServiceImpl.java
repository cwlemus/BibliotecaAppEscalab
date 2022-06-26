package com.escalab.biblioteca.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.escalab.biblioteca.model.Autor;
import com.escalab.biblioteca.repository.IAutorRepo;
import com.escalab.biblioteca.service.IAutorService;
@Service
public class AutorServiceImpl implements IAutorService {

	private final IAutorRepo autorRepo;
	
	
	@Autowired
	public AutorServiceImpl(IAutorRepo autorRepo) {
		super();
		this.autorRepo = autorRepo;
	}

	@Override
	public Autor registrar(Autor obj) {
		return this.autorRepo.save(obj);		
	}

	@Override
	public Autor modificar(Autor obj) {
		Optional<Autor> findAutor = autorRepo.findById(obj.getCodigoAutor());
		if(findAutor.isPresent()) {
			return autorRepo.save(obj);
		}else {
			return new Autor();
		}
	}

	@Override
	public List<Autor> listar() {		
		return autorRepo.findAll();
	}

	@Override
	public Autor leerPorId(Integer id) {
		Optional<Autor> findAutor = autorRepo.findById(id);
		if(findAutor.isPresent()) {
			return findAutor.get();
		}else {
			return new Autor();
		}
	}

	@Override
	public boolean eliminar(Integer id) {		
	 autorRepo.deleteById(id);
	 return true;
	}

}
