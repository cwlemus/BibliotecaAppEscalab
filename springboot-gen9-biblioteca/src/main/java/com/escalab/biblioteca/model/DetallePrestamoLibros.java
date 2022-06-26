package com.escalab.biblioteca.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
@Entity
@Table(name = "detalle_prestamo_libro")
public class DetallePrestamoLibros {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	@Column(name = "codigo_detalle_prestamo", nullable = false)
	private Integer codigoDetallePrestamo;
	@ManyToOne
	@JoinColumn(name = "codigo_prestamo", nullable = false, foreignKey = @ForeignKey(name = "FK_detelle_prestamo"))
	private PrestamoLibro prestamo;
	@ManyToOne
	@JoinColumn(name = "codigo_libro", nullable = false, foreignKey = @ForeignKey(name = "FK_detalle_libro"))	
	private Libro libro;
	
	
	//CONSTRUCTORES
	
	public DetallePrestamoLibros() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	public DetallePrestamoLibros(Integer codigoDetallePrestamo, PrestamoLibro prestamo, Libro libro) {
		super();
		this.codigoDetallePrestamo = codigoDetallePrestamo;
		this.prestamo = prestamo;
		this.libro = libro;
	}



	//GETTER Y SETTERS
	public Integer getCodigoDetallePrestamo() {
		return codigoDetallePrestamo;
	}
	public void setCodigoDetallePrestamo(Integer codigoDetallePrestamo) {
		this.codigoDetallePrestamo = codigoDetallePrestamo;
	}
	public PrestamoLibro getPrestamo() {
		return prestamo;
	}
	public void setPrestamo(PrestamoLibro prestamo) {
		this.prestamo = prestamo;
	}
	public Libro getLibro() {
		return libro;
	}
	public void setLibro(Libro libro) {
		this.libro = libro;
	}



	@Override
	public String toString() {
		return "DetallePrestamoLibros [codigoDetallePrestamo=" + codigoDetallePrestamo + ", prestamo=" + prestamo
				+ ", libro=" + libro + "]";
	}



	@Override
	public int hashCode() {
		return Objects.hash(codigoDetallePrestamo, libro, prestamo);
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DetallePrestamoLibros other = (DetallePrestamoLibros) obj;
		return Objects.equals(codigoDetallePrestamo, other.codigoDetallePrestamo) && Objects.equals(libro, other.libro)
				&& Objects.equals(prestamo, other.prestamo);
	}
	
	
	
	
	
}
