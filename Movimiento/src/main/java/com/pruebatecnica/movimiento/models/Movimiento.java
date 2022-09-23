package com.pruebatecnica.movimiento.models;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "movimiento")
public class Movimiento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_movimiento")
	private Long idMovimiento;
	
	@Column(name = "id_cuenta")
	private Long idCuenta;
	
	private LocalDateTime fecha;
	
	@Column(name = "tipo_movimiento")
	private String tipoMovimiento;
	
	private double valor;
	
	private double saldo;
	
}
