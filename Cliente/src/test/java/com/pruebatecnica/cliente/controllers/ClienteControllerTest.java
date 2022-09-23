package com.pruebatecnica.cliente.controllers;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pruebatecnica.cliente.models.Cliente;
import com.pruebatecnica.cliente.service.ClienteService;

@SpringBootTest
@AutoConfigureMockMvc
public class ClienteControllerTest {

	private static final String ENDPOINT_GRABAR_CLIENTE = "/api/clientes";
	private static final String ENDPOINT_LISTAR_CLIENTE = "/api/clientes";
	
	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private ClienteService clienteService;
	
	private List<Cliente> listaClientes;
	
	@BeforeEach
	public void setup() {
		listaClientes = Arrays.asList(
				new Cliente(1L,"Robert Martinez","M",24,"97657600","Av segunda 324","097548555", "14666", true),
				new Cliente(2L,"Michael Sanchez","M",37,"46456321","Av Principal 23","097548888", "16433", true));
	}
	
	@Test
	public void registroSatisfactorioDeCliente() throws JsonProcessingException, Exception {
		
		Cliente cliente = new Cliente(3L,"Marianela Montalvo","F",30,"87654321","Amazonas y  NNUU","097548965", "12334", true);
		ResponseEntity<Cliente> response = ResponseEntity.ok().body(cliente);
		
		when(clienteService.grabar(Mockito.any())).thenReturn(response);
		
		ObjectMapper mapper = new ObjectMapper();
		
		mvc.perform(post(ENDPOINT_GRABAR_CLIENTE)
				.content(mapper.writeValueAsString(cliente))
				.contentType(MediaType.APPLICATION_JSON)
			    .accept(MediaType.APPLICATION_JSON))
				.andDo(print())
		.andExpect(status().isOk());
	}
	
	@Test
	public void listarTodosClientes() throws Exception {
		ResponseEntity<List<Cliente>> response = ResponseEntity.ok().body(listaClientes);
		
		when(clienteService.listarClientes()).thenReturn(response);
		
		mvc.perform(get(ENDPOINT_LISTAR_CLIENTE)
			    .accept(MediaType.APPLICATION_JSON))
				.andDo(print())
		.andExpect(status().isOk());
	}
	

}
