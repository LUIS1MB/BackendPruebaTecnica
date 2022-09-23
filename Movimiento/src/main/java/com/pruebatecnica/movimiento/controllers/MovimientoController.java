package com.pruebatecnica.movimiento.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pruebatecnica.movimiento.models.Movimiento;
import com.pruebatecnica.movimiento.models.Reporte;
import com.pruebatecnica.movimiento.service.MovimientoService;

@RestController
@RequestMapping("/api/movimientos")
public class MovimientoController {

	@Autowired
	MovimientoService service;
	
	@PostMapping
	public ResponseEntity<?> grabar(@RequestBody Movimiento movimiento){
		return service.grabar(movimiento);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> actualizar(@RequestBody Movimiento movimiento, @PathVariable Long id){
		return service.actualizar(id, movimiento);
	}
	
	@GetMapping
	public ResponseEntity<List<Movimiento>> listarCuentas(){
		return service.listarMovimientos();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Movimiento> buscarCuenta(@PathVariable Long id){
		return service.buscarMovimiento(id);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> eliminarCuenta(@PathVariable Long id){
		return service.eliminarMovimiento(id);
	}
	
	@GetMapping("/reportes")
	public ResponseEntity<List<Reporte>> estadoCuenta(@RequestParam String fechaInicial, @RequestParam String fechaFin, @RequestParam String persona){
		return service.estadoCuenta(fechaInicial, fechaFin, persona);
	}
	
}
