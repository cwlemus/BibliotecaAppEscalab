package com.escalab.biblioteca.model;

import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonManagedReference;
@Entity
@Table(name = "libro")
public class Libro {
@Id	
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Integer codigoLibro;
@Column(name = "nombre_libro", nullable = false, length =50)
@Size(min = 3, message = "Nombres debe tener minimo 3 caracteres")
private String nombreLibro;
@Column(name = "stock", nullable = false)
private Integer stock;
@ManyToMany(fetch = FetchType.EAGER)
@JoinTable(name = "autor_libro", joinColumns = @JoinColumn(name = "codigo_libro", referencedColumnName = "codigoLibro"), inverseJoinColumns = @JoinColumn(name = "codigo_autor", referencedColumnName = "codigoAutor"))
private List<Autor> autores;


//CONSTRUCTORES

public Libro() {
	// TODO Auto-generated constructor stub
}



public Libro(Integer codigoLibro, String nombreLibro, Integer stock, List<Autor> autores) {
	super();
	this.codigoLibro = codigoLibro;
	this.nombreLibro = nombreLibro;
	this.stock = stock;
	this.autores = autores;
}



//GETTER Y SETTERS
public Integer getCodigoLibro() {
	return codigoLibro;
}
public void setCodigoLibro(Integer codigoLibro) {
	this.codigoLibro = codigoLibro;
}
public String getNombreLibro() {
	return nombreLibro;
}
public void setNombreLibro(String nombreLibro) {
	this.nombreLibro = nombreLibro;
}
public Integer getStock() {
	return stock;
}
public void setStock(Integer stock) {
	this.stock = stock;
}
public List<Autor> getAutores() {
	return autores;
}
public void setAutores(List<Autor> autores) {
	this.autores = autores;
}



@Override
public int hashCode() {
	return Objects.hash(autores, codigoLibro, nombreLibro, stock);
}



@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	Libro other = (Libro) obj;
	return Objects.equals(autores, other.autores) && Objects.equals(codigoLibro, other.codigoLibro)
			&& Objects.equals(nombreLibro, other.nombreLibro) && Objects.equals(stock, other.stock);
}




}
