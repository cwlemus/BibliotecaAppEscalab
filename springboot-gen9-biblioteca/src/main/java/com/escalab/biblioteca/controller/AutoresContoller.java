package com.escalab.biblioteca.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.escalab.biblioteca.model.Autor;
import com.escalab.biblioteca.service.IAutorService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/autor")
public class AutoresContoller {
	
	private final IAutorService autorService;
	
	@Autowired
	public AutoresContoller(IAutorService autorService) {
		this.autorService = autorService;
	}
	
	/**************************************** LISTAR TODOS LOS AUTORES ************************************/
	
	@ApiOperation(value = "Obtener todos los autores",
            notes = "No necesita parametros de entrada",
            responseContainer = "Autores")
    @ApiResponse(code = 200, message = "ApiResponseMessages.ITEM_FETCHED",
            response = Autor.class, responseContainer = "List"
    )   
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Bad request o datos no enviados correctamente"),
            @ApiResponse(code = 404, message = "Not found, Autores no encontrado"),
            @ApiResponse(code = 405, message = "No se encontraron autores en la BD"),
            @ApiResponse(code = 200, message = "Petición OK")})
	@GetMapping
	public ResponseEntity<List<Autor>> listar(){
		List<Autor> lista = autorService.listar();
		if(lista.isEmpty()) {
			throw new ModeloNotFoundException("No se encontraron elementos para mostrar ");
		}
		return new ResponseEntity<List<Autor>>(lista, HttpStatus.OK);
	}
	
	
	/**************************************** LISTAR AUTORES POR ID ************************************/
	
	 @ApiOperation(value = "Obtener todos el autores por su identificador",
	            notes = "No necesita parametros de entrada",
	            response = Autor.class,
	            responseContainer = "Autor")
	    @ApiResponses(value = {
	            @ApiResponse(code = 400, message = "Bad request o datos no enviados correctamente"),
	            @ApiResponse(code = 404, message = "Not found, no encontrado"),
	            @ApiResponse(code = 405, message = "No se encontraron autor en la BD"),
	            @ApiResponse(code = 200, message = "Petición OK")})
	   
	  @GetMapping("/{id}")
	  public ResponseEntity<Autor> findById(@PathVariable("id") Integer id){
	    
		 Autor obj = autorService.leerPorId(id);
			if(obj.getCodigoAutor() == null) {
				throw new ModeloNotFoundException("ID NO ENCONTRADO " + id);
			}
			return new ResponseEntity<Autor>(obj, HttpStatus.OK);
	}
	 
	 /**************************************** REGISTRAR AUTORES ************************************/
	 
	 @ApiOperation(value = "Registrar autores",
	            responseContainer = "Autores")
	    @ApiResponse(code = 200, message = "ApiResponseMessages.ITEM_FETCHED",
	            response = Autor.class, responseContainer = "List"
	    )   
	    @ApiResponses(value = {
	            @ApiResponse(code = 400, message = "Bad request o datos no enviados correctamente"),
	            @ApiResponse(code = 404, message = "Not found, Autores no encontrado"),
	            @ApiResponse(code = 405, message = "No se encontraron autores en la BD"),
	            @ApiResponse(code = 200, message = "Petición OK")})
	 
	 @ApiImplicitParams({ @ApiImplicitParam(name = "autor", value = "datos de autor", paramType = "body", dataType =
	            "Autor") })
	 @PostMapping
	 public  ResponseEntity<Autor>  save(@RequestBody Autor autor){
		 
		 ResponseEntity<Autor> resp;
		 try {
			  resp=new  ResponseEntity<Autor>(autorService.registrar(autor),HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			throw new ModeloNotFoundException(e.getMessage());
		}		 
		 return resp;	   
		 
	 }
	 
	 /**************************************** MODIFICAR AUTORES ************************************/
	  
	 @ApiOperation(value = "Modicar autores",
	            responseContainer = "Autores")
	    @ApiResponse(code = 200, message = "ApiResponseMessages.ITEM_FETCHED",
	            response = Autor.class, responseContainer = "List"
	    )   
	 @ApiResponses(value = {
	            @ApiResponse(code = 400, message = "Bad request o datos no enviados correctamente"),
	            @ApiResponse(code = 404, message = "Not found, Autores no encontrado"),
	            @ApiResponse(code = 405, message = "No se encontraron autores en la BD"),
	            @ApiResponse(code = 200, message = "Petición OK")})
	 @PutMapping("/{id}")
	 public Autor update(@RequestBody Autor autor, @PathVariable("id") Integer id){
		 Autor obj = autorService.leerPorId(autor.getCodigoAutor());
			if(obj.getCodigoAutor() == null) {
				throw new ModeloNotFoundException("ID NO ENCONTRADO " + id);
			}
		 return autorService.modificar(autor);
	}

	 /**************************************** ELIMINAR AUTORES ************************************/
	 
	 @ApiOperation(value = "Eliminar autores",
	            responseContainer = "Autores")
	    @ApiResponse(code = 200, message = "ApiResponseMessages.ITEM_FETCHED",
	            response = Autor.class, responseContainer = "List"
	    )   
	 @ApiResponses(value = {
	            @ApiResponse(code = 400, message = "Bad request o datos no enviados correctamente"),
	            @ApiResponse(code = 404, message = "Not found, Autores no encontrado"),
	            @ApiResponse(code = 405, message = "No se encontraron autores en la BD"),
	            @ApiResponse(code = 200, message = "Petición OK")})
	 @DeleteMapping("/{id}")
	 public ResponseEntity<Object> eliminar(@PathVariable("id") Integer id){
		 Autor obj = autorService.leerPorId(id);
			if(obj.getCodigoAutor() == null) {
				throw new ModeloNotFoundException("ID NO ENCONTRADO " + id);
			}
			try {
				autorService.eliminar(id);
			} catch (Exception e) {
				// TODO: handle exception
				throw new ModeloNotFoundException(e.getMessage());
			}			
			return new ResponseEntity<Object>(HttpStatus.OK);		
		 }
	
}
