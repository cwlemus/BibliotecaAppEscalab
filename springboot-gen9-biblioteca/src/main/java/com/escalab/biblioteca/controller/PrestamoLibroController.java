package com.escalab.biblioteca.controller;

import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.escalab.biblioteca.dto.LibrosPrestadosDTO;
import com.escalab.biblioteca.model.Libro;
import com.escalab.biblioteca.model.PrestamoLibro;
import com.escalab.biblioteca.service.IPrestamoLibroService;
import com.escalab.biblioteca.utils.GenericResponse;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/prestamo")
public class PrestamoLibroController {
	
	private final IPrestamoLibroService prestamoService;
	
	public PrestamoLibroController(IPrestamoLibroService prestamoService) {
		// TODO Auto-generated constructor stub
		this.prestamoService = prestamoService;
	}
	
	/**************************************** PROCESO PARA VERIFICAR PRESTAMOS POR ESTUDIANTE ************************************/
	 
	 @ApiOperation(value = "Prestar libros",
	            responseContainer = "PrestamoLibro")
	    @ApiResponse(code = 200, message = "ApiResponseMessages.ITEM_FETCHED",
	            response = PrestamoLibro.class, responseContainer = "List"
	    )   
	 @ApiResponses(value = {
	            @ApiResponse(code = 400, message = "Bad request o datos no enviados correctamente"),
	            @ApiResponse(code = 404, message = "Not found, registro no encontrado"),
	            @ApiResponse(code = 405, message = "No se encontraron registros en la BD"),
	            @ApiResponse(code = 200, message = "Petición OK")})
	@GetMapping(value = "/{codigoEstudiante}")
	public ResponseEntity<LibrosPrestadosDTO> librosPrestadosByEstudiante(@PathVariable("codigoEstudiante") Integer codigoEstudiante){
		PrestamoLibro prestamosEstudiante = prestamoService.librosPrestadosByEstudiante(codigoEstudiante);
		LibrosPrestadosDTO librosByEstudiante= new LibrosPrestadosDTO();		
		PrestamoLibro prestamo=new PrestamoLibro();
		if(prestamosEstudiante!=null) {
			prestamo=prestamosEstudiante;
			librosByEstudiante.setEstudiante(prestamo.getEstudiante());
			librosByEstudiante.setFechaPrestamo(prestamo.getFechaPrestamo());
			librosByEstudiante.setLibroPrestado(new ArrayList<Libro>());
			prestamosEstudiante.getLstDetallePrestamoLibros().forEach(e->librosByEstudiante.getLibroPrestado().add(e.getLibro()));
		}
		return new ResponseEntity<LibrosPrestadosDTO>(librosByEstudiante, HttpStatus.OK);
	}
	
/**************************************** PROCESO PARA PRESTAR LIBROS ************************************/
	 
	 @ApiOperation(value = "Prestar libros",
	            responseContainer = "PrestamoLibro")
	    @ApiResponse(code = 200, message = "ApiResponseMessages.ITEM_FETCHED",
	            response = PrestamoLibro.class, responseContainer = "List"
	    )   
	 @ApiResponses(value = {
	            @ApiResponse(code = 400, message = "Bad request o datos no enviados correctamente"),
	            @ApiResponse(code = 404, message = "Not found, prestamo no encontrado"),
	            @ApiResponse(code = 405, message = "No se encontraron prestamos en la BD"),
	            @ApiResponse(code = 200, message = "Petición OK")})
	 
	 @PostMapping
	 public ResponseEntity<GenericResponse> save(@RequestBody PrestamoLibro prestamo){
		 GenericResponse respuesta= new GenericResponse(); 
		 Optional<PrestamoLibro> opt = Optional.of(prestamo);
		 if(opt.isPresent()) {
			 if(prestamo.getLstDetallePrestamoLibros().size()>0) {
				 try {
					 prestamo.getLstDetallePrestamoLibros().stream().peek(d->d.setPrestamo(prestamo)).collect(Collectors.toList());
					 prestamo.getLstDetallePrestamoLibros().stream().forEach(System.out::println);
					 prestamoService.registrar(prestamo);
					 respuesta.setCodigo(1);
					 respuesta.setMessage("Registro almacenado correctamente");
				 }catch(Exception e) {
					 respuesta.setCodigo(0);
					 respuesta.setMessage("Registro no se almacenó correctamente error: "+e.getMessage());
				 }				 
			 }
		 }
	    return new ResponseEntity<GenericResponse>(respuesta,HttpStatus.OK);
	 }
	
}
