package com.ingenia.banca.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ingenia.banca.model.Cuenta;
import com.ingenia.banca.model.Movimiento;
import com.ingenia.banca.model.MovimientosFilter;
import com.ingenia.banca.model.Tarjeta;
import com.ingenia.banca.service.MovimientoService;

@RestController
@RequestMapping("/API/movimientos")
public class MovimientoController {
	
	@Autowired
	private MovimientoService movimientoService;
	
	@GetMapping("/tarjeta")
	public List<Movimiento> obtenerTodosLosMovimientosPorTarjeta(@RequestBody Tarjeta tarjeta){
		return movimientoService.obtenerMovimientosDeTarjeta(tarjeta);
	}
	
	@GetMapping("/cuenta")
	public List<Movimiento> obtenerTodosLosMovimientosPorCuenta(@RequestBody Cuenta cuenta){
		return movimientoService.obtenerMovimientosDeCuenta(cuenta);
	}
	
	@GetMapping("/filtrado")
	public List<Movimiento> obtenerMovimientosPorFiltro(@RequestBody MovimientosFilter filtro){
		return movimientoService.obtenerMovimientosFiltrados(filtro);
	}
}
