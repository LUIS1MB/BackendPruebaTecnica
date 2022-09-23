package com.pruebatecnica.movimiento.models.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CuentaResponse {

	private Long idCuenta;

	private Long idPersona;

	private String numeroCuenta;

	private String tipoCuenta;

	private double saldoInicial;

	private boolean estado;

}
