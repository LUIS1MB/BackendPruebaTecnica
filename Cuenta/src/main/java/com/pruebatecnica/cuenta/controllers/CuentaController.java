package com.pruebatecnica.cuenta.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pruebatecnica.cuenta.models.Cuenta;
import com.pruebatecnica.cuenta.service.CuentaService;

@RestController
@RequestMapping("/api/cuentas")
public class CuentaController {
	
	@Autowired
	CuentaService service;
	
	@PostMapping
	public ResponseEntity<Cuenta> grabar(@RequestBody Cuenta cuenta){
		return service.grabar(cuenta);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Cuenta> actualizar(@RequestBody Cuenta cuenta, @PathVariable Long id){
		return service.actualizar(id, cuenta);
	}
	
	@GetMapping
	public ResponseEntity<List<Cuenta>> listarCuentas(){
		return service.listarCuentas();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Cuenta> buscarCuenta(@PathVariable Long id){
		return service.buscarCuenta(id);
	}
	
	@GetMapping("/persona/{idPersona}")
	public ResponseEntity<Cuenta> buscarCuentaPersona(@PathVariable Long idPersona){
		return service.buscarCuentaPersona(idPersona);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> eliminarCuenta(@PathVariable Long id){
		return service.eliminarCuenta(id);
	}

}
