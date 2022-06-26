package com.escalab.biblioteca.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.escalab.biblioteca.model.Genero;
import com.escalab.biblioteca.repository.IGeneroRepo;
import com.escalab.biblioteca.service.IGeneroService;

@Service
public class GeneroServiceImpl implements IGeneroService{
private final IGeneroRepo generoRepo;
	
	
	@Autowired
	public GeneroServiceImpl(IGeneroRepo generoRepo) {
		super();
		this.generoRepo = generoRepo;
	}

	@Override
	public Genero registrar(Genero obj) {
		return this.generoRepo.save(obj);		
	}

	@Override
	public Genero modificar(Genero obj) {
		Optional<Genero> findAutor = generoRepo.findById(obj.getCodigoGenero());
		if(findAutor.isPresent()) {
			return generoRepo.save(obj);
		}else {
			return new Genero();
		}
	}

	@Override
	public List<Genero> listar() {		
		return generoRepo.findAll();
	}

	@Override
	public Genero leerPorId(Integer id) {
		Optional<Genero> findAutor = generoRepo.findById(id);
		if(findAutor.isPresent()) {
			return findAutor.get();
		}else {
			return new Genero();
		}
	}

	@Override
	public boolean eliminar(Integer id) {		
	 generoRepo.deleteById(id);
	 return true;
	}
}
