package com.ingenia.banca.service;

import com.ingenia.banca.model.Categoria;

import java.util.List;
import java.util.Optional;

public interface CategoriaService {

    /**
     * Obtiene todas las categorías de la BD
     * @return lista de categorías
     */
    public List<Categoria> obtenerTodasCategorias();

    /**
     * Obtiene una categoría según su id
     * @param id
     * @return categoría
     */
    public Optional<Categoria> obtenerCategoriaById(Long id);

    /**
     * Crea una nueva categoría en la BD
     * @param categoria
     * @return categoría creada
     */
    public Categoria crearCategoria(Categoria categoria);
}
