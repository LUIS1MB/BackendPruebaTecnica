package com.pruebatecnica.cuenta.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.pruebatecnica.cuenta.models.Cuenta;

public interface CuentaService {
	
	public ResponseEntity<Cuenta> grabar(Cuenta cuenta);
	public ResponseEntity<Cuenta> actualizar(Long id, Cuenta cuenta);
	public ResponseEntity<List<Cuenta>> listarCuentas();
	public ResponseEntity<Cuenta> buscarCuenta(Long id);
	public ResponseEntity<String> eliminarCuenta(Long id);
	public ResponseEntity<Cuenta> buscarCuentaPersona(Long idPersona);

}
