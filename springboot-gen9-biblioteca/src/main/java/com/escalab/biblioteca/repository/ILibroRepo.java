package com.escalab.biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.escalab.biblioteca.model.Libro;

public interface ILibroRepo extends JpaRepository<Libro,Integer> {
	
}
