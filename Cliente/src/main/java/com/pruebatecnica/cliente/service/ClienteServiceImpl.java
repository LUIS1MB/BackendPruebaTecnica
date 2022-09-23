package com.pruebatecnica.cliente.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.pruebatecnica.cliente.models.Cliente;
import com.pruebatecnica.cliente.repository.ClienteRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ClienteServiceImpl implements ClienteService {
	
	@Autowired
	private ClienteRepository repository;

	@Override
	public ResponseEntity<Cliente> grabar(Cliente cliente) {
		try {
			repository.save(cliente);
			return ResponseEntity.ok().body(cliente);
		} catch (Exception e) {
			log.error("Error al grabar cliente, error: {}",e.getMessage());
			return ResponseEntity.internalServerError().build();
		}
	}	

	@Override
	public ResponseEntity<Cliente> actualizar(Long id, Cliente cliente) {
		try {
			Optional<Cliente> buscarCliente = repository.findById(id);
			if(buscarCliente.isPresent()) {
				repository.save(cliente);
				return ResponseEntity.ok().body(cliente);	
			}
			return ResponseEntity.notFound().build();
		} catch (Exception e) {
			log.error("Error al actualizar cliente, error: {}",e.getMessage());
			return ResponseEntity.internalServerError().build();
		}
	}

	@Override
	public ResponseEntity<List<Cliente>> listarClientes() {
		try {
			return ResponseEntity.ok().body(repository.findAll());	
		} catch (Exception e) {
			log.error("Error al listar todos los clientes, error: {}",e.getMessage());
			return ResponseEntity.internalServerError().build();
		}
	}

	@Override
	public ResponseEntity<Cliente> buscarCliente(Long id) {
		try {
			Optional<Cliente> buscarCliente = repository.findById(id);
			if(buscarCliente.isPresent()) {
				return ResponseEntity.ok().body(buscarCliente.get());	
			}
			return ResponseEntity.notFound().build();
		} catch (Exception e) {
			log.error("Error al buscar un cliente, error: {}",e.getMessage());
			return ResponseEntity.internalServerError().build();
		}
	}

	@Override
	public ResponseEntity<String> eliminarCliente(Long id) {
		try {
			Optional<Cliente> buscarCliente = repository.findById(id);
			if(buscarCliente.isPresent()) {
				repository.delete(buscarCliente.get());
				return ResponseEntity.ok().body("Cliente eliminado");	
			}
			return ResponseEntity.notFound().build();
		} catch (Exception e) {
			log.error("Error al eliminar cliente, error: {}",e.getMessage());
			return ResponseEntity.internalServerError().build();
		}
	}

}
