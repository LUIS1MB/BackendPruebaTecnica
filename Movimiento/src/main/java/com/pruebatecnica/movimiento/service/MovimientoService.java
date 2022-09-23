package com.pruebatecnica.movimiento.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.pruebatecnica.movimiento.models.Movimiento;
import com.pruebatecnica.movimiento.models.Reporte;

public interface MovimientoService {

	public ResponseEntity<?> grabar(Movimiento movimiento);
	public ResponseEntity<?> actualizar(Long id, Movimiento movimiento);
	public ResponseEntity<List<Movimiento>> listarMovimientos();
	public ResponseEntity<Movimiento> buscarMovimiento(Long id);
	public ResponseEntity<String> eliminarMovimiento(Long id);
	public ResponseEntity<List<Reporte>> estadoCuenta(String fechaInicio, String fechaFin, String idPersona);
}
