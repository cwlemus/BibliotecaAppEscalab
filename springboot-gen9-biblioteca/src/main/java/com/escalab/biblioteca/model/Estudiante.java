package com.escalab.biblioteca.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;
@Entity
@Table(name = "estudiante")
public class Estudiante {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	@Column(name = "codigo_estudiante", nullable = false)
	private Integer codigoEstudiante;
	@Column(name = "nombre_estudiante", nullable = false, length =50)
	@Size(min = 3, message = "Nombres debe tener minimo 3 caracteres")
	private String nombreEstudiante;
	@Column(name = "apellido_estudiante", nullable = false, length =50)
	@Size(min = 3, message = "Nombres debe tener minimo 3 caracteres")
	private String apellidoEstudiante;

	//CONSTRUCTORES
	public Estudiante() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	public Estudiante(Integer codigoEstudiante, String nombreEstudiante, String apellidoEstudiante) {
		super();
		this.codigoEstudiante = codigoEstudiante;
		this.nombreEstudiante = nombreEstudiante;
		this.apellidoEstudiante = apellidoEstudiante;
	}



	//GETTER Y SETTERS 
	public Integer getCodigoEstudiante() {
		return codigoEstudiante;
	}
	public void setCodigoEstudiante(Integer codigoEstudiante) {
		this.codigoEstudiante = codigoEstudiante;
	}
	public String getNombreEstudiante() {
		return nombreEstudiante;
	}
	public void setNombreEstudiante(String nombreEstudiante) {
		this.nombreEstudiante = nombreEstudiante;
	}
	public String getApellidoEstudiante() {
		return apellidoEstudiante;
	}
	public void setApellidoEstudiante(String apellidoEstudiante) {
		this.apellidoEstudiante = apellidoEstudiante;
	}



	@Override
	public int hashCode() {
		return Objects.hash(apellidoEstudiante, codigoEstudiante, nombreEstudiante);
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Estudiante other = (Estudiante) obj;
		return Objects.equals(apellidoEstudiante, other.apellidoEstudiante)
				&& Objects.equals(codigoEstudiante, other.codigoEstudiante)
				&& Objects.equals(nombreEstudiante, other.nombreEstudiante);
	}
	
	
	
	
	
}
