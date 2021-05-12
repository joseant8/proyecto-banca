package com.ingenia.banca.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;   // para el login

    private String nombre;

    private String apellido1;

    private String apellido2;

    // relaciones

    @ManyToMany
    @JoinTable(
            name = "usuario_cuenta",
            joinColumns = {@JoinColumn(name="usuario_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name="cuenta_id", referencedColumnName = "id")}
    )
    List<Cuenta> cuentas = new ArrayList<>();


    public Usuario() {
    }

    public Usuario(String username, String nombre, String apellido1, String apellido2) {
        this.username = username;
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public List<Cuenta> getCuentas() {
        return cuentas;
    }

    public void setCuentas(List<Cuenta> cuentas) {
        this.cuentas = cuentas;
    }
}
