package com.ingenia.banca.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

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
import com.ingenia.banca.model.Tarjeta;
import com.ingenia.banca.service.TarjetaService;

@RestController
@RequestMapping("/API/tarjetas")
public class TarjetaController {
	
	@Autowired
	private TarjetaService tarjetaService;
	
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
		return ResponseEntity.created(new URI("API/categorias/" + tarjeta.getId())).body(tarjeta);
	}
	
	/**
	 * Obtenemos la tarjeta con el numero de la tarjeta
	 * @param idTarjeta
	 * @return Devuelve el objeto Tarjeta obtenido de la base de datos
	 */
	@GetMapping(value = "/{idTarjeta}")
	public Tarjeta obtenerTarjetaPoridTarjeta(@PathVariable("idTarjeta") Long idTarjeta) {
		return tarjetaService.obtenerTarjetaById(idTarjeta);
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
}
