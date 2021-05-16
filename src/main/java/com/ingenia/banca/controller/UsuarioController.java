package com.ingenia.banca.controller;

import com.ingenia.banca.model.Usuario;
import com.ingenia.banca.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/API")
public class UsuarioController {

    @Autowired
    UsuarioService servicio;


    /**
     * Obtener un usuario según el id
     * @param id
     * @return usuario
     */
    @GetMapping("/usuarios/{id}")
    public ResponseEntity<Usuario> obtenerUsuarioById(@PathVariable Long id){
        Optional<Usuario> usuario = servicio.obtenerUsuarioById(id);
        if(usuario.isPresent()){
            return ResponseEntity.ok().body(usuario.get());
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Obtener todos los usuarios de la BD
     * @return lista de usuarios
     */
    @GetMapping("/usuarios")
    public List<Usuario> obtenerTodosUsuarios(){
        return servicio.obtenerTodosUsuarios();
    }


    /**
     * Crea un nuevo usuario. Si el 'username' indicado ya existe, no se crea el usuario.
     * @param usuario
     * @return usuario creado
     */
    @PostMapping("usuarios")
    public ResponseEntity<Usuario> crearUsuario(@RequestBody Usuario usuario) throws URISyntaxException {
        if(usuario.getId() != null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }else{
            Optional<Usuario> usuarioCreado = servicio.crearUsuario(usuario);
            if(usuarioCreado.isPresent()){
                return ResponseEntity.created(new URI("API/usuarios" + usuarioCreado.get().getId())).body(usuarioCreado.get());
            }else{
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);  // El 'username' ya existía y no se crea el nuevo usuario
            }
        }
    }
}
