package com.escalab.biblioteca.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.escalab.biblioteca.exceptions.ModeloNotFoundException;
import com.escalab.biblioteca.model.Libro;
import com.escalab.biblioteca.service.ILibroService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/libro")
public class LibroController {
	
	private final ILibroService libroService;
	
	public LibroController(ILibroService libroService) {
		// TODO Auto-generated constructor stub
		this.libroService = libroService;
	}
	
	/**************************************** LISTAR TODOS LOS LIBROS ************************************/
	
	@ApiOperation(value = "Obtener todos los libros",
            notes = "No necesita parametros de entrada",
            responseContainer = "Libro")
    @ApiResponse(code = 200, message = "ApiResponseMessages.ITEM_FETCHED",
            response = Libro.class, responseContainer = "List"
    )   
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Bad request o datos no enviados correctamente"),
            @ApiResponse(code = 404, message = "Not found, libros no encontrado"),
            @ApiResponse(code = 405, message = "No se encontraron libro en la BD"),
            @ApiResponse(code = 200, message = "Petición OK")})
	
	@GetMapping
	public ResponseEntity<List<Libro>> listar(){
		List<Libro> lista = libroService.listar();
		if(lista.isEmpty()) {
			throw new ModeloNotFoundException("No se encontraron elementos para mostrar ");
		}
		return new ResponseEntity<List<Libro>>(lista, HttpStatus.OK);
	}
	

	/**************************************** LISTAR LIBROS POR ID ************************************/
	
	 @ApiOperation(value = "Obtener todos el libros por su identificador",
	            notes = "No necesita parametros de entrada",
	            response = Libro.class,
	            responseContainer = "Libro")
	    @ApiResponses(value = {
	            @ApiResponse(code = 400, message = "Bad request o datos no enviados correctamente"),
	            @ApiResponse(code = 404, message = "Not found, no encontrado"),
	            @ApiResponse(code = 405, message = "No se encontraron autor en la BD"),
	            @ApiResponse(code = 200, message = "Petición OK")})
	   
	  @GetMapping("/{id}")
	  public ResponseEntity<Libro> findById(@PathVariable("id") Integer id){
	    
		 Libro obj = libroService.leerPorId(id);
			if(obj.getCodigoLibro() == null) {
				throw new ModeloNotFoundException("ID NO ENCONTRADO " + id);
			}
			return new ResponseEntity<Libro>(obj, HttpStatus.OK);
	}
	
	 /**************************************** REGISTRAR LIBROS ************************************/
	 
	 @ApiOperation(value = "Registrar libros",
	            responseContainer = "Libros")
	    @ApiResponse(code = 200, message = "ApiResponseMessages.ITEM_FETCHED",
	            response = Libro.class, responseContainer = "List"
	    )   
	 @ApiResponses(value = {
	            @ApiResponse(code = 400, message = "Bad request o datos no enviados correctamente"),
	            @ApiResponse(code = 404, message = "Not found, no encontrado"),
	            @ApiResponse(code = 405, message = "No se encontraron autor en la BD"),
	            @ApiResponse(code = 200, message = "Petición OK")})
	   
	 @ApiImplicitParams({ @ApiImplicitParam(name = "libro", value = "datos de libro", paramType = "body", dataType =
	            "libro") })
	 
	@PostMapping
	public  ResponseEntity<Libro> registrarLibro(@RequestBody Libro libro) {
		 
		 ResponseEntity<Libro> resp;
		 try {
			  resp=new  ResponseEntity<Libro>(libroService.registrar(libro),HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			throw new ModeloNotFoundException(e.getMessage());
		}		 
		 return resp;	   
		 
	 }	
	 
	 
	 /**************************************** MODIFICAR LIBROS ************************************/
	  
	 @ApiOperation(value = "Modicar lilbros",
	            responseContainer = "Autores")
	    @ApiResponse(code = 200, message = "ApiResponseMessages.ITEM_FETCHED",
	            response = Libro.class, responseContainer = "List"
	    )   
	 @ApiResponses(value = {
	            @ApiResponse(code = 400, message = "Bad request o datos no enviados correctamente"),
	            @ApiResponse(code = 404, message = "Not found, no encontrado"),
	            @ApiResponse(code = 405, message = "No se encontraron libro en la BD"),
	            @ApiResponse(code = 200, message = "Petición OK")})
	   
	 @PutMapping("/{id}")
	 public ResponseEntity<Libro> update(@RequestBody Libro libro, @PathVariable("id") Integer id){
		 Libro obj = libroService.leerPorId(id);
		 ResponseEntity<Libro> resp;
			if(obj.getCodigoLibro() == null) {
				throw new ModeloNotFoundException("ID NO ENCONTRADO " + id);
			}
			try {
				resp=new ResponseEntity<Libro>(libroService.modificar(libro),HttpStatus.OK);
			} catch (Exception e) {
				// TODO: handle exception
				throw new ModeloNotFoundException(e.getMessage());
			}
		 return resp;
	}
	 
	 

	 /**************************************** ELIMINAR LIBROS ************************************/
	 
	 @ApiOperation(value = "Eliminar libros",
	            responseContainer = "Libro")
	    @ApiResponse(code = 200, message = "ApiResponseMessages.ITEM_FETCHED",
	            response = Libro.class, responseContainer = "List"
	    )   
	 @ApiResponses(value = {
	            @ApiResponse(code = 400, message = "Bad request o datos no enviados correctamente"),
	            @ApiResponse(code = 404, message = "Not found, no encontrado"),
	            @ApiResponse(code = 405, message = "No se encontraron libro en la BD"),
	            @ApiResponse(code = 200, message = "Petición OK")})
	   
	 @DeleteMapping("/{id}")
	 public ResponseEntity<Object> eliminar(@PathVariable("id") Integer id){
		 Libro obj = libroService.leerPorId(id);
			if(obj.getCodigoLibro() == null) {
				throw new ModeloNotFoundException("ID NO ENCONTRADO " + id);
			}
			try {
				libroService.eliminar(id);
			} catch (Exception e) {
				// TODO: handle exception
				throw new ModeloNotFoundException(e.getMessage());
			}			
			return new ResponseEntity<Object>("Libro eliminado correctamente",HttpStatus.OK);		
		 } 
	 
	 
	 
	 
}
