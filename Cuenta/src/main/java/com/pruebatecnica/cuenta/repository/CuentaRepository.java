package com.pruebatecnica.cuenta.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pruebatecnica.cuenta.models.Cuenta;

@Repository
public interface CuentaRepository extends JpaRepository<Cuenta, Long>{
	
	public Optional<Cuenta> findByIdPersona(Long idPersona);

}
