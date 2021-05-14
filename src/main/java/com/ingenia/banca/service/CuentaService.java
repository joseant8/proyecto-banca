package com.ingenia.banca.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ingenia.banca.model.Cuenta;

@Service
public interface CuentaService {

	Cuenta crearCuenta(Cuenta cuenta);
	Cuenta obtenerCuentaById(Long idCuenta);
	List<Cuenta> obtenerCuentaByUsuarioId(Long idUsuario);

}
