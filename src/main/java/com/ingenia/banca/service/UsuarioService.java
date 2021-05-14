package com.ingenia.banca.service;

import com.ingenia.banca.model.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {

    /**
     * Obtener usuario de la BD según su id
     * @param id
     * @return usuario
     */
    public Optional<Usuario> obtenerUsuarioById(Long id);


    /**
     * Obtener los usuarios de una cuenta
     * @param id id de la cuenta
     * @return lista de usuarios
     */
    public List<Usuario> obtenerTodosUsuariosByCuentaId(Long id);


    /**
     * Crear un nuevo usuario. Si ya existe un usuario con el mismo 'username' no se crea.
     * @param usuario
     * @return usuario creado
     */
    public Optional<Usuario> crearUsuario(Usuario usuario);

}
