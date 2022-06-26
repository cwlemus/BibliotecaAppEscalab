package com.escalab.biblioteca.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonFormat;
@Entity
@Table(name = "prestamo_libro")
public class PrestamoLibro {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	@Column(name = "codigo_prestamo", nullable = false)
	private Integer codigoPrestamo;	
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm", iso = ISO.DATE_TIME)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
	private LocalDateTime fechaPrestamo;
	@ManyToOne
	@JoinColumn(name = "codigo_estudiante", nullable = false, foreignKey = @ForeignKey(name = "FK_prestamo_estudiante"))
	private Estudiante estudiante;
		
	
	@OneToMany(fetch=FetchType.EAGER, mappedBy = "prestamo", 
			cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH})
	private List<DetallePrestamoLibros> lstDetallePrestamoLibros;
	
	
	//CONSTRUCTORES
	public PrestamoLibro() {
		// TODO Auto-generated constructor stub
	}
	
	
	


	public PrestamoLibro(Integer codigoPrestamo, LocalDateTime fechaPrestamo, Estudiante estudiante,
			List<DetallePrestamoLibros> lstDetallePrestamoLibros) {
		super();
		this.codigoPrestamo = codigoPrestamo;
		this.fechaPrestamo = fechaPrestamo;
		this.estudiante = estudiante;
		this.lstDetallePrestamoLibros = lstDetallePrestamoLibros;
	}





	//GETTER Y SETTERS 
	public Integer getCodigoPrestamo() {
		return codigoPrestamo;
	}
	public void setCodigoPrestamo(Integer codigoPrestamo) {
		this.codigoPrestamo = codigoPrestamo;
	}
	public LocalDateTime getFechaPrestamo() {
		return fechaPrestamo;
	}
	public void setFechaPrestamo(LocalDateTime fechaPrestamo) {
		this.fechaPrestamo = fechaPrestamo;
	}
	public Estudiante getEstudiante() {
		return estudiante;
	}
	public void setEstudiante(Estudiante estudiante) {
		this.estudiante = estudiante;
	}
	


	public List<DetallePrestamoLibros> getLstDetallePrestamoLibros() {
		return lstDetallePrestamoLibros;
	}





	public void setLstDetallePrestamoLibros(List<DetallePrestamoLibros> lstDetallePrestamoLibros) {
		this.lstDetallePrestamoLibros = lstDetallePrestamoLibros;
	}





	@Override
	public int hashCode() {
		return Objects.hash(codigoPrestamo, estudiante, fechaPrestamo);
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PrestamoLibro other = (PrestamoLibro) obj;
		return Objects.equals(codigoPrestamo, other.codigoPrestamo)				
				&& Objects.equals(estudiante, other.estudiante) && Objects.equals(fechaPrestamo, other.fechaPrestamo);
	}
	
	
	
	
	
}
