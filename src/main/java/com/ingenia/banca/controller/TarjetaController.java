package com.ingenia.banca.controller;

import java.net.URISyntaxException;
import java.time.LocalDate;
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

import com.ingenia.banca.model.Movimiento;
import com.ingenia.banca.model.Tarjeta;
import com.ingenia.banca.payload.filter.TimeFilter;
import com.ingenia.banca.service.MovimientoService;
import com.ingenia.banca.service.TarjetaService;
import com.ingenia.banca.utils.Utils;

@RestController
@RequestMapping("/API/tarjetas")
public class TarjetaController {
	
	@Autowired
	private TarjetaService tarjetaService;
	
	@Autowired
	private MovimientoService movimientoService;
	
	/**
	 * Crea una nueva tarjeta en la DB
	 * @param tarjetaNueva la tarjeta que queremos crear
	 * @return la tarjeta creada
	 * @throws URISyntaxException 
	 */
	@PostMapping
	public ResponseEntity<Tarjeta> crearTarjeta( @RequestBody Tarjeta tarjetaNueva) throws URISyntaxException {
		// Llamamos al service para crear la tarjetas
		Tarjeta tarjeta = tarjetaService.crearTarjeta(tarjetaNueva);
		
		// Nos aseguramos de que se ha creado la nueva tarjeta
		if(tarjeta == null || tarjeta.getId() == null) {
			// En caso de no haberse creado correctamente se devolvera un Error
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
        return ResponseEntity.ok().body(tarjeta);
	}
	
	/**
	 * Obtenemos la tarjeta a través del id pasado por parámetro
	 * @param idTarjeta id de la tarjeta
	 * @return tarjeta obtenida de la base de datos
	 */
	@GetMapping(value = "/{idTarjeta}")
	public ResponseEntity<Tarjeta> obtenerTarjetaById(@PathVariable("idTarjeta") Long idTarjeta) {
		try {
		Tarjeta tarjeta =  tarjetaService.obtenerTarjetaById(idTarjeta);
        return ResponseEntity.ok().body(tarjeta);
		}catch(EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * Metodo que nos devuelve el balance del saldo de la tarjeta en un intervalo de tiempo
	 * @param idTarjeta id de la tarjeta
	 * @param filtroFecha el intervalo de tiempo (fechas)
	 * @return Balance del saldo
	 */
	@GetMapping("/balance/{idTarjeta}")
	public double balanceDiarioGlobal(@PathVariable("idTarjeta")Long idTarjeta, @RequestBody() TimeFilter filtroFecha) {
		LocalDate fechaInit = filtroFecha.getFechaInit();
		LocalDate fechaFin = filtroFecha.getFechaFin();
		List<Movimiento> listaMovimiento = movimientoService.obtenerMovimientoFechaTarjeta(idTarjeta, fechaInit, fechaFin);
		
		return Utils.obtenerSaldoDeMovimientos(listaMovimiento);
	}
}
