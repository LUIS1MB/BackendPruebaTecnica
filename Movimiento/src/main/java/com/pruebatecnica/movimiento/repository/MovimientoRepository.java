package com.pruebatecnica.movimiento.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pruebatecnica.movimiento.models.Movimiento;

@Repository
public interface MovimientoRepository extends JpaRepository<Movimiento, Long>{
	
	public Optional<Movimiento> findTop1ByIdCuentaOrderByFechaDesc(Long idCuenta);
	public List<Movimiento> findByIdCuentaAndFechaGreaterThanEqualAndFechaLessThanEqual(Long idCuenta, LocalDateTime fechaInicio, LocalDateTime fechaFin);

}
