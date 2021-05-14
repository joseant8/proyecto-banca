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
import com.ingenia.banca.utils.Utils;

@RestController
@RequestMapping("/API/movimientos")
public class MovimientoController {
	
	@Autowired
	private MovimientoService movimientoService;
	
	/**
	 * Obtiene todos los movimientos de una tarjeta
	 * @param tarjeta
	 * @return lista de movimientos de la tarjeta 
	 */
	@GetMapping("/tarjeta")
	public List<Movimiento> obtenerTodosLosMovimientosPorTarjeta(@RequestBody Tarjeta tarjeta){
		return movimientoService.obtenerMovimientosDeTarjeta(tarjeta);
	}
	
	/**
	 * Obtiene todos los movimientos de una tarjeta en un mes especificado
	 * @param idTarjeta id de la tarjeta de la que queremos los movimientos
	 * @param filtroMovimiento filtro en el que encontraremos la categoria y el mes por el que filtrar
	 * @return devuelve el lsitado de movimientos
	 */
	@GetMapping("/tarjeta/{idTarjeta}")
	public List<Movimiento> obtenerMovimientosMesTarjetaByCategoria(@PathVariable("idTarjeta") Long idTarjeta,@RequestBody MovimientoMesFilter filtroMovimiento){
		return movimientoService.obtenerMovimientosTarjetaByCategoria(idTarjeta, filtroMovimiento);
	}
	
	/**
	 * Obtiene todos los movimientos de una tarjeta en un mes especificado de forma numerica
	 * @param idTarjeta id de la tarjeta de la que queremos los movimientos
	 * @param filtroMovimiento filtro en el que encontraremos la categoria y el mes por el que filtrar
	 * @return devuelve un double con el valor del balande en el mes filtrado y la categoria filtrada Ex. -43.5 / 432
	 */
	@GetMapping("/tarjeta/{idTarjeta}/balance")
	public double obtenerMovimientosTarjetaByCategoriaBalance(@PathVariable("idTarjeta") Long idTarjeta,@RequestBody MovimientoMesFilter filtroMovimiento){
		List<Movimiento> listaMovimientos = movimientoService.obtenerMovimientosTarjetaByCategoria(idTarjeta, filtroMovimiento);
		return Utils.obtenerSaldoDeMovimientos(listaMovimientos);

	}
	
	/**
	 * Obtenemos todos los movimientos de una cuenta
	 * @param cuenta la cuenta de la que queremos obtener los movimientos
	 * @return devuelve la lsita de movimientos de la cuenta
	 */
	@GetMapping("/cuenta")
	public List<Movimiento> obtenerTodosLosMovimientosPorCuenta(@RequestBody Cuenta cuenta){
		return movimientoService.obtenerMovimientosDeCuenta(cuenta);
	}
	
	/**
	 * Obtenemos todos los movimientos de una cuenta filtrandolo por mes y por categoria
	 * @param idCuenta id de la cuenta la cuenta de la que queremos obtener los movimientos
	 * @param filtroMovimiento filtro en el que encontraremos la categoria y el mes por el que filtrar
	 * @return devuelve el listado de movimientos
	 */
	@GetMapping("/cuenta/{idCuenta}")
	public List<Movimiento> obtenerMovimientosMesCuentaByCategoria(@PathVariable("idCuenta") Long idCuenta,@RequestBody MovimientoMesFilter filtroMovimiento){
		return movimientoService.obtenerMovimientosCuentaByCategoria(idCuenta, filtroMovimiento);
	}
	
	/**
	 * Obtenemos todos los movimientos de una cuenta filtrandolo por mes y por categoria de forma numerica
	 * @param idCuenta id de la cuenta la cuenta de la que queremos obtener los movimientos
	 * @param filtroMovimiento filtro en el que encontraremos la categoria y el mes por el que filtrar
	 * @return devuelve el valor del balance de una cuenta en un mes y categoria determinada Ex. -435.3 / 543.98
	 */
	@GetMapping("/cuenta/{idCuenta}/balance")
	public double obtenerMovimientosMesCuentaByCategoriaBalance(@PathVariable("idCuenta") Long idCuenta,@RequestBody MovimientoMesFilter filtroMovimiento){
		List<Movimiento> listaMovimientos = movimientoService.obtenerMovimientosCuentaByCategoria(idCuenta, filtroMovimiento);
		return Utils.obtenerSaldoDeMovimientos(listaMovimientos);
	}
	
	/**
	 * Obtiene los movimientos filtrandos por los parametros especificados
	 * @param filtro objeto con los valores por los que se quiere filtrar los movimientos
	 * @return devuelve el listado de movimientos
	 */
	@GetMapping("/filtrado")
	public List<Movimiento> obtenerMovimientosPorFiltro(@RequestBody MovimientosFilter filtro){
		return movimientoService.obtenerMovimientosFiltrados(filtro);
	}
	
	/**
	 * Obtiene los movimientos de un usuario filtrado por los mes y categoria
	 * @param idUsuario id del usuario del que queremos los movimientos
	 * @param filtroMovimiento filtro en el que encontraremos la categoria y el mes por el que filtrar
	 * @return devuelve el listado de movimientos
	 */
	@GetMapping("/usuario/{idUsuario}")
	public List<Movimiento> obtenerMovimientosMesUsuarioByCategoria(@PathVariable("idUsuario") Long idUsuario,@RequestBody MovimientoMesFilter filtroMovimiento){
		return movimientoService.obtenerMovimientosUsuarioByCategoria(idUsuario, filtroMovimiento);
	}
	
	/**
	 * Obtiene los movimientos de un usuario filtrado por los mes y categoria
	 * @param idUsuario id del usuario del que queremos los movimientos
	 * @param filtroMovimiento filtro en el que encontraremos la categoria y el mes por el que filtrar
	 * @return devuelve el valor del balance de una cuenta en un mes y categoria determinada Ex. -435.3 / 543.98
	 */
	@GetMapping("/usuario/{idUsuario}/balance")
	public double obtenerMovimientosMesUsuarioByCategoriaBalance(@PathVariable("idUsuario") Long idUsuario,@RequestBody MovimientoMesFilter filtroMovimiento){
		List<Movimiento> listaMovimientos = movimientoService.obtenerMovimientosUsuarioByCategoria(idUsuario, filtroMovimiento);
		return Utils.obtenerSaldoDeMovimientos(listaMovimientos);

	}

}
