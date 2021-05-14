package com.ingenia.banca.controller;

import com.ingenia.banca.model.Usuario;
import com.ingenia.banca.payload.request.LoginRequest;
import com.ingenia.banca.payload.request.SignupRequest;
import com.ingenia.banca.payload.response.JwtResponse;
import com.ingenia.banca.payload.response.MessageResponse;
import com.ingenia.banca.repository.UsuarioRepository;
import com.ingenia.banca.security.jwt.JwtTokenUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/API/auth")
public class AuthController<JwtUtils> {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UsuarioRepository usuarioRepositorio;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> authenticateUser(@RequestBody LoginRequest loginRequest){

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtTokenUtil.generateJwtToken(authentication);

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        Optional<Usuario> userLoggedIn = usuarioRepositorio.findByUsername(loginRequest.getUsername());

        return ResponseEntity.ok(new JwtResponse(jwt, userLoggedIn.get()));
    }

    @PostMapping("/signup")
    public ResponseEntity<MessageResponse> registerUser(@RequestBody SignupRequest signUpRequest) {

        // Comprueba: username
        if (usuarioRepositorio.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("No se ha podido registrar. El nombre de usuario ya existe."));
        }

        // Crea nueva cuenta de usuario
        Usuario user = new Usuario(signUpRequest.getUsername(),
                            signUpRequest.getNombreCompleto(),
                            encoder.encode(signUpRequest.getPassword()));

        usuarioRepositorio.save(user);

        return ResponseEntity.ok(new MessageResponse("Usuario registrado correctamente."));
    }
}
