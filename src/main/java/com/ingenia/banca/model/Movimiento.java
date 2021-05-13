package com.ingenia.banca.model;

import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Movimiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreatedDate
    @Column(nullable = false)
    private Date fecha = new Date();

    @Column(nullable = false)
    private Double cantidad;

    @Column(nullable = false)
    private TipoMovimiento tipo;

    //relaciones

    @ManyToOne
    @JoinColumn(name= "categoria_id")
    private Categoria categoria;

    @ManyToOne
    @JoinColumn(name="cuenta_id", nullable = false)
    private Cuenta cuenta;

    @ManyToOne
    @JoinColumn(name= "tarjeta_id")
    private Tarjeta tarjeta;


    public Movimiento() {
    }

    public Movimiento(Double cantidad, TipoMovimiento tipo, Categoria categoria, Cuenta cuenta, Tarjeta tarjeta) {
        this.cantidad = cantidad;
        this.tipo = tipo;
        this.categoria = categoria;
        this.cuenta = cuenta;
        this.tarjeta = tarjeta;
    }

    public Long getId() {
        return id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Double getCantidad() {
        return cantidad;
    }

    public void setCantidad(Double cantidad) {
        this.cantidad = cantidad;
    }

    public TipoMovimiento getTipo() {
        return tipo;
    }

    public void setTipo(TipoMovimiento tipo) {
        this.tipo = tipo;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Cuenta getCuenta() {
        return cuenta;
    }

    public void setCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
    }

    public Tarjeta getTarjeta() {
        return tarjeta;
    }

    public void setTarjeta(Tarjeta tarjeta) {
        this.tarjeta = tarjeta;
    }
}
