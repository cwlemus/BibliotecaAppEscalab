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
@Table(name = "genero")
public class Genero {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "codigo_genero", nullable = false)
	private Integer codigoGenero;
	@Column(name = "genero", nullable = false, length =70)
	@Size(min = 3, message = "Nombres debe tener minimo 3 caracteres")
	private String genero;
	
	
	//CONSTRUCTORES
	
	public Genero() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	public Genero(Integer codigoGenero, String genero) {
		super();
		this.codigoGenero = codigoGenero;
		this.genero = genero;
	}



	//GETTER Y SETTERS
	public Integer getCodigoGenero() {
		return codigoGenero;
	}
	public void setCodigoGenero(Integer codigoGenero) {
		this.codigoGenero = codigoGenero;
	}
	public String getGenero() {
		return genero;
	}
	public void setGenero(String genero) {
		this.genero = genero;
	}



	@Override
	public int hashCode() {
		return Objects.hash(codigoGenero, genero);
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Genero other = (Genero) obj;
		return Objects.equals(codigoGenero, other.codigoGenero) && Objects.equals(genero, other.genero);
	}
	
	
	
	
}
