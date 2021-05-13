package com.ingenia.banca.controller;

import com.ingenia.banca.model.Categoria;
import com.ingenia.banca.service.CategoriaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/API")
public class CategoriaController {

    private CategoriaService servicio;

    /**
     * Obtener todas las categorías
     * @return lista de categorías
     */
    @GetMapping("/categorias")
    public List<Categoria> obtenerTodasCategorias(){
        return servicio.obtenerTodasCategorias();
    }


    /**
     * Obtener una categoría según el id
     * @param id
     * @return
     */
    @GetMapping("/categorias/{id}")
    public ResponseEntity<Categoria> obtenerCategoriaById(@PathVariable Long id){
        Optional<Categoria> categoria = servicio.obtenerCategoriaById(id);
        if(categoria.isPresent()){
            return ResponseEntity.ok().body(categoria.get());
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    /**
     * Crea una nueva categoría en la BD
     * @param categoria
     * @return la nueva categoría creada
     * @throws URISyntaxException
     */
    @PostMapping("/categorias")
    public ResponseEntity<Categoria> crearCategoria(@RequestBody Categoria categoria) throws URISyntaxException {
        if(categoria.getId() != null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Categoria categoriaCreada = servicio.crearCategoria(categoria);
        return ResponseEntity.created(new URI("API/categorias/" + categoriaCreada.getId())).body(categoriaCreada);
    }




}
