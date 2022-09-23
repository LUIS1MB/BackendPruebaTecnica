package com.pruebatecnica.cliente.models;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "cliente")
@PrimaryKeyJoinColumn(referencedColumnName = "id_persona")
public class Cliente extends Persona {
	
	
	
	public Cliente(Long idPersona, String nombre, String genero, int edad, String identificacion, String direccion,
			String telefono, String contrasena, boolean estado) {
		super(idPersona, nombre, genero, edad, identificacion, direccion, telefono);
		this.contrasena = contrasena;
		this.estado = estado;
	}

	private String contrasena;
	
	private boolean estado;
	
}
