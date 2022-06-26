package com.escalab.biblioteca.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.escalab.biblioteca.model.Estudiante;
import com.escalab.biblioteca.repository.IEstudianteRepo;
import com.escalab.biblioteca.service.IEstudianteService;

@Service
public class EstudianteServiceImpl implements IEstudianteService {

private final IEstudianteRepo estudianteRepo;
	
	
	@Autowired
	public EstudianteServiceImpl(IEstudianteRepo estudianteRepo) {
		super();
		this.estudianteRepo = estudianteRepo;
	}

	@Override
	public Estudiante registrar(Estudiante obj) {
		return this.estudianteRepo.save(obj);		
	}

	@Override
	public Estudiante modificar(Estudiante obj) {
		Optional<Estudiante> findAutor = estudianteRepo.findById(obj.getCodigoEstudiante());
		if(findAutor.isPresent()) {
			return estudianteRepo.save(obj);
		}else {
			return new Estudiante();
		}
	}

	@Override
	public List<Estudiante> listar() {		
		return estudianteRepo.findAll();
	}

	@Override
	public Estudiante leerPorId(Integer id) {
		Optional<Estudiante> findAutor = estudianteRepo.findById(id);
		if(findAutor.isPresent()) {
			return findAutor.get();
		}else {
			return new Estudiante();
		}
	}

	@Override
	public boolean eliminar(Integer id) {		
	 estudianteRepo.deleteById(id);
	 return true;
	}

}
