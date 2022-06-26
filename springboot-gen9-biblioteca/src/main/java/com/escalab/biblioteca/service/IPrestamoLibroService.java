package com.escalab.biblioteca.service;

import java.util.List;

import com.escalab.biblioteca.model.DetallePrestamoLibros;
import com.escalab.biblioteca.model.PrestamoLibro;

public interface IPrestamoLibroService extends ICRUD<PrestamoLibro> {

	public PrestamoLibro librosPrestadosByEstudiante(Integer codigoEstudiante);
}
