package com.ingenia.banca.service.impl;

import com.ingenia.banca.model.Categoria;
import com.ingenia.banca.model.Cuenta;
import com.ingenia.banca.model.Movimiento;
import com.ingenia.banca.model.Tarjeta;
import com.ingenia.banca.model.TipoMovimiento;
import com.ingenia.banca.model.Filter.MovimientoMesFilter;
import com.ingenia.banca.model.Filter.MovimientosFilter;
import com.ingenia.banca.repository.CategoriaRepository;
import com.ingenia.banca.repository.CuentaRepository;
import com.ingenia.banca.repository.MovimientoRepository;
import com.ingenia.banca.repository.TarjetaRepository;
import com.ingenia.banca.service.MovimientoService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MovimientoServiceImpl implements MovimientoService {
	@Autowired
	MovimientoRepository movimientoRepository;
	
	@Autowired
	CategoriaRepository categoriaRepository;
	
	@Autowired
	CuentaRepository cuentaRepository;
	
	@Autowired
	TarjetaRepository tarjetaRepository;

	@Transactional
	@Override
	public List<Movimiento> obtenerMovimientosDeTarjeta(Tarjeta tarjeta) {
		// Obtenermos todos los movimientos con el metodo del repositorio
		return movimientoRepository.obtenerMovimientosDeTarjeta(tarjeta.getId());
	}

	@Transactional
	@Override
	public List<Movimiento> obtenerMovimientosDeCuenta(Cuenta cuenta) {
		// Obtenemos todos los movimientos de una cuenta
		return movimientoRepository.obtenerMovimientosDeCuenta(cuenta.getId());
	}

	@Transactional
	@Override
	public List<Movimiento> obtenerMovimientosFiltrados(MovimientosFilter filtro) {
		Long idMovimiento = filtro.getId();
		if(idMovimiento!=null) {
			Movimiento movimiento=  movimientoRepository.findById(idMovimiento).orElseThrow(()
					-> new EntityNotFoundException("No se ha encontrado movimiento con id: "+idMovimiento));
			List<Movimiento> listaMovimiento =new  ArrayList<Movimiento>();
			listaMovimiento.add(movimiento);
			return listaMovimiento;
		}else {
			
			Date fecha =  filtro.getFecha();
			Double cantidad = filtro.getCantidad();
			TipoMovimiento tipo = filtro.getTipo();
			Long categoriaId = filtro.getCategoriaId();
			Long cuentaId = filtro.getCuentaId();
			Long tarjetaId = filtro.getTarjetaId();
			
			Categoria categoriaBuscada = null;
			Cuenta cuentaBuscada = null;
			Tarjeta tarjetaBuscada = null;
			if(categoriaId != null) {
				try {
					categoriaBuscada = categoriaRepository.findById(categoriaId).orElseThrow(()
							-> new EntityNotFoundException("No se ha encontrado categoria con id: "+categoriaId));
				}catch(EntityNotFoundException e) {
					
				}
			}
			if(cuentaId != null) {
				try {
					cuentaBuscada = cuentaRepository.findById(cuentaId).orElseThrow(()
							-> new EntityNotFoundException("No se ha encontrado cuenta con id: "+cuentaId));
				}catch(EntityNotFoundException e) {
					
				}
			}
			if(tarjetaId != null) {
				try {
					tarjetaBuscada = tarjetaRepository.findById(cuentaId).orElseThrow(()
							-> new EntityNotFoundException("No se ha encontrado tarjeta con id: "+tarjetaId));
				}catch(EntityNotFoundException e) {
					
				}	    	
			}
			
			Movimiento movimientoFiltro = new Movimiento();
			movimientoFiltro.setCantidad(cantidad);
			movimientoFiltro.setCategoria(categoriaBuscada);
			movimientoFiltro.setCuenta(cuentaBuscada);
			movimientoFiltro.setFecha(fecha);
			movimientoFiltro.setTarjeta(tarjetaBuscada);
			movimientoFiltro.setTipo(tipo);
			
			return movimientoRepository.findAll(Example.of(movimientoFiltro));
		}
	}

	@Override
	public List<Movimiento> obtenerMovimientoFechaTarjeta(Long idTarjeta, LocalDate fechaInit, LocalDate fechaFin) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date dateInit = null;
		Date datefin = null;
		try {
			dateInit = formatter.parse(fechaInit.toString());
			datefin = formatter.parse(fechaFin.toString());
		} catch (ParseException e) {
		} 
		return movimientoRepository.obtenerMovimientosDeTarjetaFechas(idTarjeta, dateInit , datefin);
		
	}

	@Override
	public List<Movimiento> obtenerMovimientosCuentaByCategoria(Long idCuenta, MovimientoMesFilter filtroMovimiento) {
		Date fechaInit = new Date();
		Date fechaFin = new Date();
		try {
			fechaInit= obtenerFechaInicio(filtroMovimiento);
			fechaFin = obtenerFechaFin(filtroMovimiento, fechaInit);
			
		} catch (ParseException e) {
		}
		Long idCategoria = filtroMovimiento.getIdCategoria();
		
		return movimientoRepository.obtenerMovimientosDeCuentaByFechaAndCategoria(idCuenta,idCategoria ,fechaInit, fechaFin);
	}
	
	@Override
	public List<Movimiento> obtenerMovimientosTarjetaByCategoria(Long idTarjeta, MovimientoMesFilter filtroMovimiento) {
		Date fechaInit = new Date();
		Date fechaFin = new Date();
		try {
			fechaInit= obtenerFechaInicio(filtroMovimiento);
			fechaFin = obtenerFechaFin(filtroMovimiento, fechaInit);
			
		} catch (ParseException e) {
		}
		Long idCategoria = filtroMovimiento.getIdCategoria();
		return movimientoRepository.obtenerMovimientosDeTarjetaByFechaAndCategoria(idTarjeta,idCategoria ,fechaInit, fechaFin);
	}
	

	@Override
	public List<Movimiento> obtenerMovimientosUsuarioByCategoria(Long idUsuario, MovimientoMesFilter filtroMovimiento) {
		Date fechaInit = new Date();
		Date fechaFin = new Date();
		try {
			fechaInit= obtenerFechaInicio(filtroMovimiento);
			fechaFin = obtenerFechaFin(filtroMovimiento, fechaInit);
			
		} catch (ParseException e) {
		}
		Long idCategoria = filtroMovimiento.getIdCategoria();
		return movimientoRepository.obtenerMovimientosDeUsuarioByFechaAndCategoria(idUsuario,idCategoria ,fechaInit, fechaFin);
	}
	
	private Date obtenerFechaInicio(MovimientoMesFilter filtroMovimiento) throws ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		int anoActual = LocalDate.now().getYear();
		String mes = filtroMovimiento.getMes();
		return dateFormat.parse(anoActual+"-"+mes+"-01");
	}
	
	private Date obtenerFechaFin(MovimientoMesFilter filtroMovimiento,Date fechaInit) throws ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		int anoActual = LocalDate.now().getYear();
		String mes = filtroMovimiento.getMes();
		int ultimoDia = ponerDiasFechaFinMes(fechaInit);
		return dateFormat.parse(anoActual+"-"+mes+"-"+ultimoDia);

		
	}
	
	private static int ponerDiasFechaFinMes(Date fecha){

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fecha); // Configuramos la fecha que se recibe
        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

 }




}
