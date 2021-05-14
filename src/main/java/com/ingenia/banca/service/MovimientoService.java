package com.ingenia.banca.service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ingenia.banca.model.Cuenta;
import com.ingenia.banca.model.Movimiento;
import com.ingenia.banca.model.Tarjeta;
import com.ingenia.banca.model.Filter.MovimientoMesFilter;
import com.ingenia.banca.model.Filter.MovimientosFilter;

@Service
public interface MovimientoService {
	
	public List<Movimiento> obtenerMovimientosDeTarjeta(Tarjeta tarjeta);
	
	public List<Movimiento> obtenerMovimientosDeCuenta(Cuenta cuenta);

	public List<Movimiento> obtenerMovimientosFiltrados(MovimientosFilter filtro);
	
	public List<Movimiento> obtenerMovimientoFechaTarjeta(Long idTarjeta,LocalDate fechaInit, LocalDate fechaFin);

	public List<Movimiento> obtenerMovimientosCuentaByCategoria(Long idCuenta, MovimientoMesFilter filtroMovimiento);

	public List<Movimiento> obtenerMovimientosTarjetaByCategoria(Long idTarjeta, MovimientoMesFilter filtroMovimiento);

	public List<Movimiento> obtenerMovimientosUsuarioByCategoria(Long idUsuario, MovimientoMesFilter filtroMovimiento);
}
