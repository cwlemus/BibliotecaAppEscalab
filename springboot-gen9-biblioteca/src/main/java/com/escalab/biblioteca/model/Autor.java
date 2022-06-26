package com.escalab.biblioteca.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Información o propiedes del autor")
@Entity
@Table(name = "autor")
public class Autor {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)	
private Integer codigoAutor;

@ApiModelProperty(notes = "Nombre dene tener como minimo 3 caracteres")
@Size(min = 3, message = "Nombres debe tener minimo 3 caracteres")

@Column(name = "nombre_autor", nullable = false, length =50)
private String nombreAutor;

@ApiModelProperty(notes = "Apellido debe tener como minimo 3 caracteres")
@Size(min = 3, message = "Nombres debe tener minimo 3 caracteres")
@Column(name = "apellido_autor", nullable = false, length =50)
private String apellidoAutor;


@ManyToOne
@JoinColumn(name = "codigo_genero", nullable = false, foreignKey = @ForeignKey(name = "FK_genero_autor"))
private Genero genero;


//CONSTRUCTORES
public Autor() {
	// TODO Auto-generated constructor stub
}


public Autor(Integer codigoAutor, String nombreAutor, String apellidoAutor, Genero genero) {
	super();
	this.codigoAutor = codigoAutor;
	this.nombreAutor = nombreAutor;
	this.apellidoAutor = apellidoAutor;
	this.genero = genero;
}



//GETTER Y SETTERS
public Integer getCodigoAutor() {
	return codigoAutor;
}
public void setCodigoAutor(Integer codigoAutor) {
	this.codigoAutor = codigoAutor;
}
public String getNombreAutor() {
	return nombreAutor;
}
public void setNombreAutor(String nombreAutor) {
	this.nombreAutor = nombreAutor;
}
public String getApellidoAutor() {
	return apellidoAutor;
}
public void setApellidoAutor(String apellidoAutor) {
	this.apellidoAutor = apellidoAutor;
}
public Genero getGenero() {
	return genero;
}
public void setGenero(Genero genero) {
	this.genero = genero;
}



}
