package com.ingenia.banca.controller;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ingenia.banca.model.Cuenta;
import com.ingenia.banca.model.Movimiento;
import com.ingenia.banca.model.Tarjeta;
import com.ingenia.banca.model.Filter.MovimientoMesFilter;
import com.ingenia.banca.model.Filter.MovimientosFilter;
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
	@GetMapping("/tarjeta/{idTarjeta}")
	public List<Movimiento> obtenerMovimientosTarjetaByCategoria(@PathVariable("idTarjeta") Long idTarjeta,@RequestBody MovimientoMesFilter filtroMovimiento){
		return movimientoService.obtenerMovimientosTarjetaByCategoria(idTarjeta, filtroMovimiento);
	}
	
	@GetMapping("/cuenta")
	public List<Movimiento> obtenerTodosLosMovimientosPorCuenta(@RequestBody Cuenta cuenta){
		return movimientoService.obtenerMovimientosDeCuenta(cuenta);
	}
	
	@GetMapping("/cuenta/{idCuenta}")
	public List<Movimiento> obtenerMovimientosCuentaByCategoria(@PathVariable("idCuenta") Long idCuenta,@RequestBody MovimientoMesFilter filtroMovimiento){
		return movimientoService.obtenerMovimientosCuentaByCategoria(idCuenta, filtroMovimiento);
	}
	
	@GetMapping("/filtrado")
	public List<Movimiento> obtenerMovimientosPorFiltro(@RequestBody MovimientosFilter filtro){
		return movimientoService.obtenerMovimientosFiltrados(filtro);
	}
	
	@GetMapping("/usuario/{idUsuario}")
	public List<Movimiento> obtenerMovimientosUsuarioByCategoria(@PathVariable("idUsuario") Long idUsuario,@RequestBody MovimientoMesFilter filtroMovimiento){
		return movimientoService.obtenerMovimientosUsuarioByCategoria(idUsuario, filtroMovimiento);
	}

}
