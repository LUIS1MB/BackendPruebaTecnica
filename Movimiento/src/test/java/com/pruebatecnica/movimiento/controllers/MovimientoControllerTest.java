package com.pruebatecnica.movimiento.controllers;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;
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
import com.pruebatecnica.movimiento.models.Movimiento;
import com.pruebatecnica.movimiento.service.MovimientoService;

@SpringBootTest
@AutoConfigureMockMvc
class MovimientoControllerTest {

	private static final String ENDPOINT_GRABAR_MOVIMIENTO = "/api/movimientos";
	private static final String ENDPOINT_LISTAR_MOVIMIENTO = "/api/movimientos";
	
	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private MovimientoService movimientoService;
	
	private List<Movimiento> listaMovimientos;
	
	
	@BeforeEach
	public void setup() {
		listaMovimientos = Arrays.asList(
				new Movimiento(1L,1L,LocalDateTime.of(2022, 9, 22, 10, 10),"DEPOSITO",100L,100L),
				new Movimiento(2L,1L,LocalDateTime.of(2022, 9, 22, 11, 13),"DEPOSITO",100L,200L));
	}
	
	@Test
	public void registroSatisfactorioMovimiento() throws JsonProcessingException, Exception{
		LocalDateTime actual = LocalDateTime.of(2022, 9, 22, 21, 13);
		Movimiento movimiento = new Movimiento(3L,1L,actual,"DEPOSITO",100L,300L);
		ResponseEntity response = ResponseEntity.ok().body(movimiento);
		
		when(movimientoService.grabar(Mockito.any())).thenReturn(response);
		
		ObjectMapper mapper = new ObjectMapper();
		
		mvc.perform(post(ENDPOINT_GRABAR_MOVIMIENTO)
				.content(mapper.writeValueAsString(movimiento))
				.contentType(MediaType.APPLICATION_JSON)
			    .accept(MediaType.APPLICATION_JSON))
				.andDo(print())
		.andExpect(status().isOk());
	}
	
	@Test
	public void listarTodasLosMovimientos() throws Exception {
		ResponseEntity<List<Movimiento>> response = ResponseEntity.ok().body(listaMovimientos);
		
		when(movimientoService.listarMovimientos()).thenReturn(response);
		
		mvc.perform(get(ENDPOINT_LISTAR_MOVIMIENTO)
			    .accept(MediaType.APPLICATION_JSON))
				.andDo(print())
		.andExpect(status().isOk());
	}
	
}
