package com.pruebatecnica.cliente.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.pruebatecnica.cliente.models.Cliente;

public interface ClienteService {
	
	public ResponseEntity<Cliente> grabar(Cliente cliente);
	public ResponseEntity<Cliente> actualizar(Long id, Cliente cliente);
	public ResponseEntity<List<Cliente>> listarClientes();
	public ResponseEntity<Cliente> buscarCliente(Long id);
	public ResponseEntity<String> eliminarCliente(Long id);
	
}
