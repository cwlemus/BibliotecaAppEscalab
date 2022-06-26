package com.escalab.biblioteca.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.escalab.biblioteca.model.Estudiante;
import com.escalab.biblioteca.model.Libro;

public class LibrosPrestadosDTO {
	private Estudiante estudiante;
	private LocalDateTime fechaPrestamo;
	private List<Libro> libroPrestado;
	public Estudiante getEstudiante() {
		return estudiante;
	}
	public void setEstudiante(Estudiante estudiante) {
		this.estudiante = estudiante;
	}
	public LocalDateTime getFechaPrestamo() {
		return fechaPrestamo;
	}
	public void setFechaPrestamo(LocalDateTime fechaPrestamo) {
		this.fechaPrestamo = fechaPrestamo;
	}
	public List<Libro> getLibroPrestado() {
		return libroPrestado;
	}
	public void setLibroPrestado(List<Libro> libroPrestado) {
		this.libroPrestado = libroPrestado;
	}
	
	
	
}
