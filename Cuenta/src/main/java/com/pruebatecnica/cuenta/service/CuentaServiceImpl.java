package com.pruebatecnica.cuenta.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.pruebatecnica.cuenta.models.Cuenta;
import com.pruebatecnica.cuenta.repository.CuentaRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CuentaServiceImpl implements CuentaService {
	
	@Autowired
	CuentaRepository repository;

	@Override
	public ResponseEntity<Cuenta> grabar(Cuenta cuenta) {
		try {
			repository.save(cuenta);
			return ResponseEntity.ok().body(cuenta);
		} catch (Exception e) {
			log.error("Error al grabar cuenta, error: {}",e.getMessage());
			return ResponseEntity.internalServerError().build();
		}
	}

	@Override
	public ResponseEntity<Cuenta> actualizar(Long id, Cuenta cuenta) {
		try {
			Optional<Cuenta> buscarCuenta = repository.findById(id);
			if(buscarCuenta.isPresent()) {
				repository.save(cuenta);
				return ResponseEntity.ok().body(cuenta);	
			}
			return ResponseEntity.notFound().build();
		} catch (Exception e) {
			log.error("Error al actualizar cuenta, error: {}",e.getMessage());
			return ResponseEntity.internalServerError().build();
		}
	}

	@Override
	public ResponseEntity<List<Cuenta>> listarCuentas() {
		try {
			return ResponseEntity.ok().body(repository.findAll());	
		} catch (Exception e) {
			log.error("Error al listar todas las cuentas, error: {}",e.getMessage());
			return ResponseEntity.internalServerError().build();
		}
	}

	@Override
	public ResponseEntity<Cuenta> buscarCuenta(Long id) {
		try {
			Optional<Cuenta> buscarCuenta = repository.findById(id);
			if(buscarCuenta.isPresent()) {
				return ResponseEntity.ok().body(buscarCuenta.get());	
			}
			return ResponseEntity.notFound().build();
		} catch (Exception e) {
			log.error("Error al buscar una cuenta, error: {}",e.getMessage());
			return ResponseEntity.internalServerError().build();
		}
	}
	
	@Override
	public ResponseEntity<String> eliminarCuenta(Long id) {
		try {
			Optional<Cuenta> buscarCuenta = repository.findById(id);
			if(buscarCuenta.isPresent()) {
				repository.delete(buscarCuenta.get());
				return ResponseEntity.ok().body("Cuenta eliminada");	
			}
			return ResponseEntity.notFound().build();
		} catch (Exception e) {
			log.error("Error al eliminar cuenta, error: {}",e.getMessage());
			return ResponseEntity.internalServerError().build();
		}
	}

	@Override
	public ResponseEntity<Cuenta> buscarCuentaPersona(Long idPersona) {
		try {
			Optional<Cuenta> buscarCuenta = repository.findByIdPersona(idPersona);
			if(buscarCuenta.isPresent()) {
				return ResponseEntity.ok().body(buscarCuenta.get());	
			}
			return ResponseEntity.notFound().build();
		} catch (Exception e) {
			log.error("Error al buscar una cuenta por persona, error: {}",e.getMessage());
			return ResponseEntity.internalServerError().build();
		}
	}

}
