package com.ingenia.banca.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ingenia.banca.model.Cuenta;
import com.ingenia.banca.model.Movimiento;
import com.ingenia.banca.model.Tarjeta;
import com.ingenia.banca.model.TimeFilter;
import com.ingenia.banca.model.TipoMovimiento;
import com.ingenia.banca.service.MovimientoService;
import com.ingenia.banca.service.TarjetaService;

@RestController
@RequestMapping("/API/tarjetas")
public class TarjetaController {
	
	@Autowired
	private TarjetaService tarjetaService;
	
	@Autowired
	private MovimientoService movimientoService;
	
	/**
	 * Crear nueva tarjeta en la DB
	 * @param tarjetaNueva 
	 * @return la Tarjeta creada
	 * @throws URISyntaxException 
	 */
	@PostMapping
	public ResponseEntity<Tarjeta> crearTarjeta( @RequestBody Tarjeta tarjetaNueva) throws URISyntaxException {
		// Llamamos al service para crear la tarjetas
		Tarjeta tarjeta = tarjetaService.crearTarjeta(tarjetaNueva);
		
		// Nos aseguramos de que se ha creado la nueva tarjeta
		if(tarjeta == null || tarjeta.getId() == null) {
			// En caso de no haberse creado correctamente se devolvera un Error
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return ResponseEntity.created(new URI("API/tarjetas/" + tarjeta.getId())).body(tarjeta);
	}
	
	/**
	 * Obtenemos la tarjeta con el numero de la tarjeta
	 * @param idTarjeta
	 * @return Devuelve el objeto Tarjeta obtenido de la base de datos
	 */
	@GetMapping(value = "/{idTarjeta}")
	public ResponseEntity<Tarjeta> obtenerTarjetaPoridTarjeta(@PathVariable("idTarjeta") Long idTarjeta) {
		try {
		Tarjeta tarjeta =  tarjetaService.obtenerTarjetaById(idTarjeta);
		return ResponseEntity.created(new URI("API/tarjetas/" + tarjeta.getId())).body(tarjeta);
		}catch(EntityNotFoundException | URISyntaxException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
	}
	
	/**
	 * Obtenemos la lista de las Tarjetas asociadas a una cuenta
	 * @param cuenta
	 * @return Devuelve la lista de tarjetas asignadas a una cuenta
	 */
	@GetMapping
	public List<Tarjeta> obtenerTarjetaCuenta(@RequestBody Cuenta cuenta) {
		// LLamamos al metodo del service que obtiene las tarjetas
		return tarjetaService.obtenerTarjetaByCuenta(cuenta);
	}
	
	@GetMapping("/balance/{idTarjeta}")
	public double balanceDiarioGlobal(@PathVariable("idTarjeta")Long idTarjeta, @RequestBody() TimeFilter filtroFecha) {
		double balance = 0;
		LocalDate fechaInit = filtroFecha.getFechaInit();
		LocalDate fechaFin = filtroFecha.getFechaFin();
		List<Movimiento> listaMovimiento = movimientoService.obtenerMovimientoFechaTarjeta(idTarjeta, fechaInit, fechaFin);
		
		for (Iterator iterator = listaMovimiento.iterator(); iterator.hasNext();) {
			Movimiento movimiento = (Movimiento) iterator.next();
			if(movimiento.getTipo().equals(TipoMovimiento.INGRESO)) {
				balance += movimiento.getCantidad();
			}else {
				balance -= movimiento.getCantidad();
			}
		}
		
		return balance;
	}
}
