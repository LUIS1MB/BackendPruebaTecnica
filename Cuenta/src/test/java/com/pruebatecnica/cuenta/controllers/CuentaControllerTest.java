package com.pruebatecnica.cuenta.controllers;

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
import com.pruebatecnica.cuenta.models.Cuenta;
import com.pruebatecnica.cuenta.service.CuentaService;

@SpringBootTest
@AutoConfigureMockMvc
class CuentaControllerTest {

	private static final String ENDPOINT_GRABAR_CUENTA = "/api/cuentas";
	private static final String ENDPOINT_LISTAR_CUENTA = "/api/cuentas";
	
	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private CuentaService cuentaService;
	
	private List<Cuenta> listaCuentas;
	
	@BeforeEach
	public void setup() {
		listaCuentas = Arrays.asList(new Cuenta(1L,3L,"249583744","AHORRO",150L,true),
				new Cuenta(2L,4L,"249583755","AHORRO",200L,true));
	}
	
	@Test
	public void registroSatisfactorioCuenta() throws JsonProcessingException, Exception{
		Cuenta cuenta = new Cuenta(1L,3L,"249583766","AHORRO",100L,true);
		ResponseEntity<Cuenta> response = ResponseEntity.ok().body(cuenta);
		
		when(cuentaService.grabar(Mockito.any())).thenReturn(response);
		
		ObjectMapper mapper = new ObjectMapper();
		
		mvc.perform(post(ENDPOINT_GRABAR_CUENTA)
				.content(mapper.writeValueAsString(cuenta))
				.contentType(MediaType.APPLICATION_JSON)
			    .accept(MediaType.APPLICATION_JSON))
				.andDo(print())
		.andExpect(status().isOk());
	}
	
	@Test
	public void listarTodasLasCuentas() throws Exception {
		ResponseEntity<List<Cuenta>> response = ResponseEntity.ok().body(listaCuentas);
		
		when(cuentaService.listarCuentas()).thenReturn(response);
		
		mvc.perform(get(ENDPOINT_LISTAR_CUENTA)
			    .accept(MediaType.APPLICATION_JSON))
				.andDo(print())
		.andExpect(status().isOk());
	}

}
