package com.pruebatecnica.movimiento.models;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Reporte {

	private LocalDateTime fechaMovimiento;
	private String cliente;
	private String numeroCuenta;
	private String tipoCuenta;
	private double saldoInicial;
	private boolean estado;
	private double movimiento;
	private double saldoDisponible;

}
