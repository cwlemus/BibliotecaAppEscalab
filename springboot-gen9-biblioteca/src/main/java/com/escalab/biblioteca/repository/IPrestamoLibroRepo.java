package com.escalab.biblioteca.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.escalab.biblioteca.model.DetallePrestamoLibros;
import com.escalab.biblioteca.model.PrestamoLibro;


public interface IPrestamoLibroRepo extends JpaRepository<PrestamoLibro,Integer> {
	
	@Query("from PrestamoLibro p where p.estudiante.codigoEstudiante = :codigoEstudiante")
	PrestamoLibro librosPrestadosByEstudiante(@Param("codigoEstudiante") Integer codigoEstudiante);
}
