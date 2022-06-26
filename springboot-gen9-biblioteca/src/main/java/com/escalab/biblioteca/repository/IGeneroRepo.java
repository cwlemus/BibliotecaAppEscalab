package com.escalab.biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.escalab.biblioteca.model.Genero;

public interface IGeneroRepo extends JpaRepository<Genero, Integer> {

}
