package com.ingenia.banca.repository;

import com.ingenia.banca.model.Movimiento;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MovimientoRepository extends JpaRepository<Movimiento, Long> {
	
	@Query("SELECT m FROM Movimiento m WHERE m.tarjeta.id= :idTarjeta")
	List<Movimiento> obtenerMovimientosDeTarjeta(@Param("idTarjeta") Long idTarjeta);
	
	@Query("SELECT m FROM Movimiento m WHERE m.cuenta.id= :idCuenta")
	List<Movimiento> obtenerMovimientosDeCuenta(@Param("idCuenta") Long idcuenta);
	
}
