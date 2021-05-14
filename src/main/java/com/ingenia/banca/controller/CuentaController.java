package com.ingenia.banca.controller;

import java.time.LocalDate;
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
import com.ingenia.banca.model.Movimiento;
import com.ingenia.banca.model.Filter.TimeFilter;
import com.ingenia.banca.service.CuentaService;
import com.ingenia.banca.service.MovimientoService;
import com.ingenia.banca.utils.Utils;

@RestController
@RequestMapping("/API/cuentas")
public class CuentaController {
	
	@Autowired 
	private CuentaService cuentaService;

	@Autowired
	private MovimientoService movimientoService;
	
	/**
	 * Metodo para crear nueva cuenta
	 * @param cuenta Cuenta que queremos guardar en base de datos
	 * @return devuelve la cuenta ya creada
	 */
	@PostMapping
	public ResponseEntity<Cuenta> crearCuenta(@RequestBody Cuenta cuenta){
		Cuenta cuentaCreada = cuentaService.crearCuenta(cuenta);
		if(cuentaCreada == null || cuenta.getId() ==null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
        return ResponseEntity.ok().body(cuentaCreada);
		
	}
	
	/**
	 * Metodo que nos devuelve la cuenta que corresponde al id que s ele pasa de parametro
	 * @param idCuenta id de la cuenta a buscar
	 * @return Nos devuvle el objeto cuenta que ha encontrado con el id 
	 */
	@GetMapping("/{idCuenta}")
	public ResponseEntity<Cuenta> obtenerCuentaById(@PathVariable("idCuenta")Long idCuenta){
		try {
			Cuenta cuenta = cuentaService.obtenerCuentaById(idCuenta);			
			return ResponseEntity.ok().body(cuenta);
		}catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	/**
	 * Obtiene la lista de las cuentas que pertenecen al usuario con el id pasado
	 * @param idUsuario id del usuario del que queremos buscar las cuenas
	 * @return listado de cuentas pertenecientes al usuario con id pasado por parametro
	 */
	@GetMapping("/usuario/{idUsuario}")
	public List<Cuenta> obtenerCuentaByUserId(@PathVariable("idUsuario") Long idUsuario){
		return cuentaService.obtenerCuentaByUsuarioId(idUsuario);
	}
	
	/**
	 * Metodo que nos devuelve el balance de la cuenta de un usuario pasado por parametro en un intervalode fechas deseado
	 * @param idUsuario usuario del que se quiere ver el balance
	 * @param filtroFecha fechas en las que se queire ver el valance
	 * @return Numero que equivale al balance durante este periodo de tiempo
	 */
	@GetMapping("/usuario/balance/{idUsuario}")
	public double obtenerBalanceCuentaByUserId(@PathVariable("idUsuario") Long idUsuario,  @RequestBody TimeFilter filtroFecha){
		LocalDate fechaInit = filtroFecha.getFechaInit();
		LocalDate fechaFin = filtroFecha.getFechaFin();
		List<Movimiento> listaMovimiento= movimientoService.obtenerMovimientoFechaCuenta(idUsuario,fechaInit,fechaFin);
		double balance = Utils.obtenerSaldoDeMovimientos(listaMovimiento);
		
		return balance;
	}
	
	
}
