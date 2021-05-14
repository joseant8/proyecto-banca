package com.ingenia.banca.service.impl;

import com.ingenia.banca.model.Cuenta;
import com.ingenia.banca.model.Usuario;
import com.ingenia.banca.repository.CuentaRepository;
import com.ingenia.banca.repository.UsuarioRepository;
import com.ingenia.banca.service.UsuarioService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    UsuarioRepository usuarioRepositorio;
    CuentaRepository cuentaRepositorio;   // lo usaremos para las asociaciones entre cuentas y usuarios

    public UsuarioServiceImpl(UsuarioRepository usuarioRepositorio, CuentaRepository cuentaRepositorio) {
        this.usuarioRepositorio = usuarioRepositorio;
        this.cuentaRepositorio = cuentaRepositorio;
    }

    @Override
    public Optional<Usuario> obtenerUsuarioById(Long id) {
        return usuarioRepositorio.findById(id);
    }

    @Override
    public List<Usuario> obtenerTodosUsuariosByCuentaId(Long id) {
        Optional<Cuenta> cuenta = cuentaRepositorio.findById(id);
        if(cuenta.isPresent()){
            return cuenta.get().getUsuarios();
        }else{
            return new ArrayList<Usuario>();  // lista vacía
        }
    }

    @Override
    public Optional<Usuario> crearUsuario(Usuario usuario) {
        // miramos si existe ya un usuario con el 'username' indicado
        Optional<Usuario> usuarioDB = usuarioRepositorio.findByUsername(usuario.getUsername());
        if(usuarioDB.isEmpty()){
            Usuario usuarioCreado = usuarioRepositorio.save(usuario);
            return Optional.of(usuarioCreado);
        }else{
            return Optional.empty();
        }
    }
}
