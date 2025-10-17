package com.naisa.commons.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


import com.naisa.commons.services.CommonService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CommonController <RQ, RS, S extends CommonService<RQ, RS>> {
	
	protected S service;
	
	
	@GetMapping
	public ResponseEntity<List<RS>> listar(){
		return ResponseEntity.ok(service.listar());
	}
	
	//segunda forma 
	@GetMapping("/{id}")
    public ResponseEntity<RS> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.obtenerPorId(id));
    }
	
	@PostMapping        
	public ResponseEntity<RS> insertar(@Valid @RequestBody RQ request){   //TERCER MANERA DE USAR RESPONSE ENITTY
		return new ResponseEntity<>(service.insertar(request),HttpStatus.CREATED);
		
	}

	
	@PutMapping("/{id}")        
	public ResponseEntity<RS> actualizar(@Valid @RequestBody RQ request,
			@PathVariable Long id){   //TERCER MANERA DE USAR RESPONSE ENITTY
		return ResponseEntity.ok(service.actualizar(request,id));
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<RS> eliminarPorId(@PathVariable Long id){     //segunda manera de usar un ResponseEntity
		service.eliminar(id);
		return ResponseEntity.noContent().build();
	}
	
	
	
	
	
	
	

}
