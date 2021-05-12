package com.ingenia.banca.repository;

import com.ingenia.banca.model.Tarjeta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TarjetaRepository extends JpaRepository<Tarjeta, Long> {
}
