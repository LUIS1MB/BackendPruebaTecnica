package com.pruebatecnica.movimiento.models.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteResponse {

	private Long idPersona; 
	
	private String nombre;
	
	private String genero;
	
	private int edad;
	
	private String identificacion;
	
	private String direccion;
	
	private String telefono;
	
}
