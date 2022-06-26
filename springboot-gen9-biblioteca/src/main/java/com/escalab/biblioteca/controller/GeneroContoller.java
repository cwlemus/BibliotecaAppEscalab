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
import com.escalab.biblioteca.model.Genero;
import com.escalab.biblioteca.service.IGeneroService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/genero")
public class GeneroContoller {
	
	private final IGeneroService generoService;
	
	@Autowired
	public GeneroContoller(IGeneroService generoService) {
		this.generoService = generoService;
	}
	
	/**************************************** LISTAR TODOS LOS GENEROS ************************************/
	
	@ApiOperation(value = "Obtener todos los generos",
            notes = "No necesita parametros de entrada",
            responseContainer = "Autores")
    @ApiResponse(code = 200, message = "ApiResponseMessages.ITEM_FETCHED",
            response = Genero.class, responseContainer = "List"
    )   
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Bad request o datos no enviados correctamente"),
            @ApiResponse(code = 404, message = "Not found, Autores no encontrado"),
            @ApiResponse(code = 405, message = "No se encontraron generos en la BD"),
            @ApiResponse(code = 200, message = "Petición OK")})
	@GetMapping
	public ResponseEntity<List<Genero>> listar(){
		List<Genero> lista = generoService.listar();
		if(lista.isEmpty()) {
			throw new ModeloNotFoundException("No se encontraron elementos para mostrar ");
		}
		return new ResponseEntity<List<Genero>>(lista, HttpStatus.OK);
	}
	
	
	/**************************************** LISTAR GENEROS POR ID ************************************/
	
	 @ApiOperation(value = "Obtener todos el generos por su identificador",
	            notes = "No necesita parametros de entrada",
	            response = Genero.class,
	            responseContainer = "Genero")
	    @ApiResponses(value = {
	            @ApiResponse(code = 400, message = "Bad request o datos no enviados correctamente"),
	            @ApiResponse(code = 404, message = "Not found, no encontrado"),
	            @ApiResponse(code = 405, message = "No se encontraron genero en la BD"),
	            @ApiResponse(code = 200, message = "Petición OK")})
	   
	  @GetMapping("/{id}")
	  public ResponseEntity<Genero> findById(@PathVariable("id") Integer id){
	    
		 Genero obj = generoService.leerPorId(id);
			if(obj.getCodigoGenero() == null) {
				throw new ModeloNotFoundException("ID NO ENCONTRADO " + id);
			}
			return new ResponseEntity<Genero>(obj, HttpStatus.OK);
	}
	 
	 /**************************************** REGISTRAR GENEROS ************************************/
	 
	 @ApiOperation(value = "Registrar generos",
	            responseContainer = "Autores")
	    @ApiResponse(code = 200, message = "ApiResponseMessages.ITEM_FETCHED",
	            response = Genero.class, responseContainer = "List"
	    )   
	    @ApiResponses(value = {
	            @ApiResponse(code = 400, message = "Bad request o datos no enviados correctamente"),
	            @ApiResponse(code = 404, message = "Not found, Autores no encontrado"),
	            @ApiResponse(code = 405, message = "No se encontraron generos en la BD"),
	            @ApiResponse(code = 200, message = "Petición OK")})
	 
	 @ApiImplicitParams({ @ApiImplicitParam(name = "genero", value = "datos de genero", paramType = "body", dataType =
	            "Genero") })
	 @PostMapping
	 public  ResponseEntity<Genero>  save(@RequestBody Genero genero){
		 
		 ResponseEntity<Genero> resp;
		 try {
			  resp=new  ResponseEntity<Genero>(generoService.registrar(genero),HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			throw new ModeloNotFoundException(e.getMessage());
		}		 
		 return resp;	   
		 
	 }
	 
	 /**************************************** MODIFICAR GENEROS ************************************/
	  
	 @ApiOperation(value = "Modicar generos",
	            responseContainer = "Autores")
	    @ApiResponse(code = 200, message = "ApiResponseMessages.ITEM_FETCHED",
	            response = Genero.class, responseContainer = "List"
	    )   
	 @ApiResponses(value = {
	            @ApiResponse(code = 400, message = "Bad request o datos no enviados correctamente"),
	            @ApiResponse(code = 404, message = "Not found, Autores no encontrado"),
	            @ApiResponse(code = 405, message = "No se encontraron generos en la BD"),
	            @ApiResponse(code = 200, message = "Petición OK")})
	 @PutMapping("/{id}")
	 public ResponseEntity<Genero> update(@RequestBody Genero genero, @PathVariable("id") Integer id){
		 Genero obj = generoService.leerPorId(genero.getCodigoGenero());
		 ResponseEntity<Genero> resp;
			if(obj.getCodigoGenero() == null) {
				throw new ModeloNotFoundException("ID NO ENCONTRADO " + id);
			}
			try {
				resp= new ResponseEntity<Genero>(generoService.modificar(genero),HttpStatus.OK);
			} catch (Exception e) {
				// TODO: handle exception
				throw new ModeloNotFoundException(e.getMessage());
			}
		 return resp;
	}

	 /**************************************** ELIMINAR GENEROS ************************************/
	 
	 @ApiOperation(value = "Eliminar generos",
	            responseContainer = "Autores")
	    @ApiResponse(code = 200, message = "ApiResponseMessages.ITEM_FETCHED",
	            response = Genero.class, responseContainer = "List"
	    )   
	 @ApiResponses(value = {
	            @ApiResponse(code = 400, message = "Bad request o datos no enviados correctamente"),
	            @ApiResponse(code = 404, message = "Not found, Autores no encontrado"),
	            @ApiResponse(code = 405, message = "No se encontraron generos en la BD"),
	            @ApiResponse(code = 200, message = "Petición OK")})
	 @DeleteMapping("/{id}")
	 public ResponseEntity<Object> eliminar(@PathVariable("id") Integer id){
		 Genero obj = generoService.leerPorId(id);
			if(obj.getCodigoGenero() == null) {
				throw new ModeloNotFoundException("ID NO ENCONTRADO " + id);
			}
			try {
				generoService.eliminar(id);
			} catch (Exception e) {
				// TODO: handle exception
				throw new ModeloNotFoundException(e.getMessage());
			}			
			return new ResponseEntity<Object>("Genero eliminado correctamente",HttpStatus.OK);		
		 }
	
}
