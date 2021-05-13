package com.ingenia.banca.service.impl;

import com.ingenia.banca.model.Categoria;
import com.ingenia.banca.repository.CategoriaRepository;
import com.ingenia.banca.service.CategoriaService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaServiceImpl implements CategoriaService {

    private CategoriaRepository repositorio;

    public CategoriaServiceImpl(CategoriaRepository repositorio) {
        this.repositorio = repositorio;
    }

    @Override
    public List<Categoria> obtenerTodasCategorias() {
        return repositorio.findAll();
    }

    @Override
    public Optional<Categoria> obtenerCategoriaById(Long id) {
        return repositorio.findById(id);
    }

    @Override
    public Categoria crearCategoria(Categoria categoria) {
        return repositorio.save(categoria);
    }
}
