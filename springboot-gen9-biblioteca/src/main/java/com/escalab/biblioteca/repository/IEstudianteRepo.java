package com.escalab.biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.escalab.biblioteca.model.Estudiante;

public interface IEstudianteRepo extends JpaRepository<Estudiante,Integer> {

}
