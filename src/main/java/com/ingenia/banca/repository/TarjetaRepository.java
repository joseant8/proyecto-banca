//package com.ingenia.banca.repository;
//
//import com.ingenia.banca.model.Tarjeta;
//
//import java.util.List;
//import java.util.Optional;
//
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
//import org.springframework.stereotype.Repository;
//
//@Repository
//public interface TarjetaRepository extends JpaRepository<Tarjeta, Long> {
//	
//	@Query("SELECT Tarjeta From Tarjeta t WHERE t.numero = :numeroTarjeta")
//	Optional<Tarjeta> obtenerTarjetaByNumeroTarjeta(@Param("numeroTarjeta") Long numeroTarjeta);
//	
//	@Query("SELECT Tarjeta From Tarjeta t WHERE t.cuenta = :idCuenta")
//	List<Tarjeta> findByCuentaId(@Param("idCuenta") Long idCuenta);
//}
