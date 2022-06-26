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
import com.escalab.biblioteca.model.Estudiante;
import com.escalab.biblioteca.service.IEstudianteService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/estudiante")
public class EstudianteController {
	
	private final IEstudianteService estudianteService;
	
	public EstudianteController(IEstudianteService estudianteService) {
		// TODO Auto-generated constructor stub
		this.estudianteService = estudianteService;
	}
	
	/**************************************** LISTAR TODOS LOS ESTUDIANTES ************************************/
	
	@ApiOperation(value = "Obtener todos los estudiantes",
            notes = "No necesita parametros de entrada",
            responseContainer = "Estudiante")
    @ApiResponse(code = 200, message = "ApiResponseMessages.ITEM_FETCHED",
            response = Estudiante.class, responseContainer = "List"
    )   
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Bad request o datos no enviados correctamente"),
            @ApiResponse(code = 404, message = "Not found, estudiante no encontrado"),
            @ApiResponse(code = 405, message = "No se encontraron estudiantes en la BD"),
            @ApiResponse(code = 200, message = "Petición OK")})
	
	@GetMapping
	public ResponseEntity<List<Estudiante>> listar(){
		List<Estudiante> lista = estudianteService.listar();
		if(lista.isEmpty()) {
			throw new ModeloNotFoundException("No se encontraron elementos para mostrar ");
		}
		return new ResponseEntity<List<Estudiante>>(lista, HttpStatus.OK);
	}
	

	/**************************************** LISTAR ESTUDIANTES POR ID ************************************/
	
	@ApiOperation(value = "Obtener todos los estudiantes",
            notes = "No necesita parametros de entrada",
            responseContainer = "Estudiante")
    @ApiResponse(code = 200, message = "ApiResponseMessages.ITEM_FETCHED",
            response = Estudiante.class, responseContainer = "List"
    )   
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Bad request o datos no enviados correctamente"),
            @ApiResponse(code = 404, message = "Not found, estudiante no encontrado"),
            @ApiResponse(code = 405, message = "No se encontraron estudiantes en la BD"),
            @ApiResponse(code = 200, message = "Petición OK")})
	   
	  @GetMapping("/{id}")
	  public ResponseEntity<Estudiante> findById(@PathVariable("id") Integer id){
	    
		 Estudiante obj = estudianteService.leerPorId(id);
			if(obj.getCodigoEstudiante() == null) {
				throw new ModeloNotFoundException("ID NO ENCONTRADO " + id);
			}
			return new ResponseEntity<Estudiante>(obj, HttpStatus.OK);
	}
	
	 /**************************************** REGISTRAR ESTUDIANTES ************************************/
	 
	@ApiOperation(value = "Obtener todos los estudiantes",
            notes = "No necesita parametros de entrada",
            responseContainer = "Estudiante")
    @ApiResponse(code = 200, message = "ApiResponseMessages.ITEM_FETCHED",
            response = Estudiante.class, responseContainer = "List"
    )   
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Bad request o datos no enviados correctamente"),
            @ApiResponse(code = 404, message = "Not found, estudiante no encontrado"),
            @ApiResponse(code = 405, message = "No se encontraron estudiantes en la BD"),
            @ApiResponse(code = 200, message = "Petición OK")})
	   
	 @ApiImplicitParams({ @ApiImplicitParam(name = "libro", value = "datos de libro", paramType = "body", dataType =
	            "libro") })
	 
	@PostMapping
	public  ResponseEntity<Estudiante> registrarLibro(@RequestBody Estudiante libro) {
		 
		 ResponseEntity<Estudiante> resp;
		 try {
			  resp=new  ResponseEntity<Estudiante>(estudianteService.registrar(libro),HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			throw new ModeloNotFoundException(e.getMessage());
		}		 
		 return resp;	   
		 
	 }	
	 
	 
	 /**************************************** MODIFICAR ESTUDIANTES ************************************/
	  
	@ApiOperation(value = "Obtener todos los estudiantes",
            notes = "No necesita parametros de entrada",
            responseContainer = "Estudiante")
    @ApiResponse(code = 200, message = "ApiResponseMessages.ITEM_FETCHED",
            response = Estudiante.class, responseContainer = "List"
    )   
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Bad request o datos no enviados correctamente"),
            @ApiResponse(code = 404, message = "Not found, estudiante no encontrado"),
            @ApiResponse(code = 405, message = "No se encontraron estudiantes en la BD"),
            @ApiResponse(code = 200, message = "Petición OK")})
	   
	 @PutMapping("/{id}")
	 public ResponseEntity<Estudiante> update(@RequestBody Estudiante autor, @PathVariable("id") Integer id){
		
		 Estudiante obj = estudianteService.leerPorId(autor.getCodigoEstudiante());
		 ResponseEntity<Estudiante> resp;
			if(obj.getCodigoEstudiante() == null) {
				throw new ModeloNotFoundException("ID NO ENCONTRADO " + id);
			}
			try {
				resp=new ResponseEntity<Estudiante>(estudianteService.modificar(autor),HttpStatus.OK);
			} catch (Exception e) {
				// TODO: handle exception
				throw new ModeloNotFoundException(e.getMessage());
			}
		 return resp;
	}
	 
	 

	 /**************************************** ELIMINAR ESTUDIANTES ************************************/
	 
	@ApiOperation(value = "Obtener todos los estudiantes",
            notes = "No necesita parametros de entrada",
            responseContainer = "Estudiante")
    @ApiResponse(code = 200, message = "ApiResponseMessages.ITEM_FETCHED",
            response = Estudiante.class, responseContainer = "List"
    )   
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Bad request o datos no enviados correctamente"),
            @ApiResponse(code = 404, message = "Not found, estudiante no encontrado"),
            @ApiResponse(code = 405, message = "No se encontraron estudiantes en la BD"),
            @ApiResponse(code = 200, message = "Petición OK")})
	   
	 @DeleteMapping("/{id}")
	 public ResponseEntity<Object> eliminar(@PathVariable("id") Integer id){
		 Estudiante obj = estudianteService.leerPorId(id);
			if(obj.getCodigoEstudiante() == null) {
				throw new ModeloNotFoundException("ID NO ENCONTRADO " + id);
			}
			try {
				estudianteService.eliminar(id);
			} catch (Exception e) {
				// TODO: handle exception
				throw new ModeloNotFoundException(e.getMessage());
			}			
			return new ResponseEntity<Object>("Estudiante eliminado",HttpStatus.OK);		
		 } 
	 
	 
	 
	 
}
