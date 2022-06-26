package com.escalab.biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.escalab.biblioteca.model.Autor;

public interface IAutorRepo extends JpaRepository<Autor,Integer> {

}
