package com.ingenia.banca.payload.response;

import com.ingenia.banca.model.Usuario;

public class JwtResponse {

    private String token;
    private Usuario user;

    public JwtResponse() {
    }

    public JwtResponse(String token, Usuario user) {
        this.token = token;
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Usuario getUser() {
        return user;
    }

    public void setUser(Usuario user) {
        this.user = user;
    }
}
