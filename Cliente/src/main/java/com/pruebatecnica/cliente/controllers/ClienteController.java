package com.pruebatecnica.cliente.controllers;

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

import com.pruebatecnica.cliente.models.Cliente;
import com.pruebatecnica.cliente.service.ClienteService;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {
	
	@Autowired
	ClienteService service;
	
	@PostMapping
	public ResponseEntity<Cliente> grabar(@RequestBody Cliente cliente){
		return service.grabar(cliente);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Cliente> actualizar(@RequestBody Cliente cliente, @PathVariable Long id){
		return service.actualizar(id, cliente);
	}
	
	@GetMapping
	public ResponseEntity<List<Cliente>> listarClientes(){
		return service.listarClientes();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Cliente> buscarCliente(@PathVariable Long id){
		return service.buscarCliente(id);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> eliminarCliente(@PathVariable Long id){
		return service.eliminarCliente(id);
	}
	
		
}
