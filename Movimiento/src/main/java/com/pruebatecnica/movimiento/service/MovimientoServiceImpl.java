package com.pruebatecnica.movimiento.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.pruebatecnica.movimiento.models.Movimiento;
import com.pruebatecnica.movimiento.models.Reporte;
import com.pruebatecnica.movimiento.models.response.CuentaResponse;
import com.pruebatecnica.movimiento.models.response.ClienteResponse;
import com.pruebatecnica.movimiento.repository.MovimientoRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MovimientoServiceImpl implements MovimientoService {

	@Value("${cuenta.url}")
	private String cuentaUrl;
	
	@Value("${cliente.url}")
	private String clienteUrl;

	@Autowired
	MovimientoRepository repository;

	@Autowired
	private RestTemplate restTemplate;

	@Override
	public ResponseEntity<Object> grabar(Movimiento movimiento) {
		try {
			if (validarCuenta(movimiento.getIdCuenta()).getIdCuenta() != null) {
				double saldoActualizado = calcularSaldo(movimiento.getIdCuenta(), movimiento.getValor(), movimiento.getSaldo());

				if (saldoActualizado < 0) {
					return ResponseEntity.badRequest().body("Saldo no disponible");
				}

				movimiento.setSaldo(saldoActualizado);
				movimiento.setFecha(LocalDateTime.now());
				repository.save(movimiento);
				return ResponseEntity.ok().body(movimiento);
			}
			return ResponseEntity.notFound().build();

		} catch (Exception e) {
			log.error("Error al grabar movimiento, error: {}", e.getMessage());
			return ResponseEntity.internalServerError().build();
		}
	}

	@Override
	public ResponseEntity<Object> actualizar(Long id, Movimiento movimiento) {
		try {
			Optional<Movimiento> buscarMovimiento = repository.findById(id);
			if (buscarMovimiento.isPresent()) {
				double saldoActualizado = calcularSaldo(movimiento.getIdCuenta(), movimiento.getValor(), movimiento.getSaldo());

				if (saldoActualizado < 0) {
					return ResponseEntity.badRequest().body("Saldo no disponible");
				}
				repository.save(movimiento);
				return ResponseEntity.ok().body(movimiento);
			}
			return ResponseEntity.notFound().build();
		} catch (Exception e) {
			log.error("Error al actualizar movimiento, error: {}", e.getMessage());
			return ResponseEntity.internalServerError().build();
		}
	}

	@Override
	public ResponseEntity<List<Movimiento>> listarMovimientos() {
		try {
			return ResponseEntity.ok().body(repository.findAll());
		} catch (Exception e) {
			log.error("Error al listar todos los movimientos, error: {}", e.getMessage());
			return ResponseEntity.internalServerError().build();
		}
	}

	@Override
	public ResponseEntity<Movimiento> buscarMovimiento(Long id) {
		try {
			Optional<Movimiento> buscarMovimiento = repository.findById(id);
			if (buscarMovimiento.isPresent()) {
				return ResponseEntity.ok().body(buscarMovimiento.get());
			}
			return ResponseEntity.notFound().build();
		} catch (Exception e) {
			log.error("Error al buscar un movimiento, error: {}", e.getMessage());
			return ResponseEntity.internalServerError().build();
		}
	}

	@Override
	public ResponseEntity<String> eliminarMovimiento(Long id) {
		try {
			Optional<Movimiento> buscarMovimiento = repository.findById(id);
			if (buscarMovimiento.isPresent()) {
				repository.delete(buscarMovimiento.get());
				return ResponseEntity.ok().body("Movimiento eliminado");
			}
			return ResponseEntity.notFound().build();
		} catch (Exception e) {
			log.error("Error al eliminar movimiento, error: {}", e.getMessage());
			return ResponseEntity.internalServerError().build();
		}
	}

	@Override
	public ResponseEntity<List<Reporte>> estadoCuenta(String fechaInicio, String fechaFin, String idPersona) {
		try {
			
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); 
			LocalDateTime inicio = LocalDate.parse(fechaInicio, formatter).atStartOfDay();
			LocalDateTime fin = LocalDate.parse(fechaFin, formatter).atStartOfDay();

			CuentaResponse cuentaResponse = restTemplate.getForObject(cuentaUrl.concat("/persona/" + idPersona),CuentaResponse.class);
			List<Movimiento> listaMovimientos = repository.findByIdCuentaAndFechaGreaterThanEqualAndFechaLessThanEqual(cuentaResponse.getIdCuenta(), inicio, fin);
			ClienteResponse clienteResponse = restTemplate.getForObject(clienteUrl.concat("/"+idPersona), ClienteResponse.class);
			
			
			return ResponseEntity.ok().body(armarListaEstadoCuenta(listaMovimientos, cuentaResponse, clienteResponse));

		} catch (Exception e) {
			log.error("Error al listar todos los movimientos, error: {}", e.getMessage());
			return ResponseEntity.internalServerError().build();
		}
	}
	
	private CuentaResponse validarCuenta(Long idCuenta){
		return restTemplate.getForObject(cuentaUrl.concat("/" + idCuenta),CuentaResponse.class);
	}

	private double calcularSaldo(Long idCuenta, double nuevoValor, double saldoInicial) {
		Optional<Movimiento> movimientoAnterior = repository.findTop1ByIdCuentaOrderByFechaDesc(idCuenta);
		double saldoActualizado = 0;
		if (movimientoAnterior.isPresent()) {
			saldoActualizado = movimientoAnterior.get().getSaldo() + nuevoValor;
		} else {
			saldoActualizado = saldoInicial + nuevoValor;
		}

		return saldoActualizado;
	}
	
	private List<Reporte> armarListaEstadoCuenta(List<Movimiento> listaMovimientos, CuentaResponse cuenta, ClienteResponse cliente){
		
		List<Reporte> listaReportes = new ArrayList<>();
		listaReportes = listaMovimientos.stream()
							.map(p->{
								return Reporte.builder()
										.fechaMovimiento(p.getFecha())
										.cliente(cliente.getNombre())
										.numeroCuenta(cuenta.getNumeroCuenta())
										.tipoCuenta(cuenta.getTipoCuenta())
										.saldoInicial(cuenta.getSaldoInicial())
										.estado(cuenta.isEstado())
										.movimiento(p.getValor())
										.saldoDisponible(p.getSaldo())
										.build(); 
							})
							.collect(Collectors.toList());
		
		return listaReportes;
	}

}
