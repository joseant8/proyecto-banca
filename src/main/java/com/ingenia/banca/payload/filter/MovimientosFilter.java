package com.ingenia.banca.payload.filter;

import java.util.Date;

import com.ingenia.banca.model.TipoMovimiento;

public class MovimientosFilter {
    private Long id;
    private Date fecha;
    private Double cantidad;
    private TipoMovimiento tipo;
    private Long categoriaId;
    private Long cuentaId;
    private Long tarjetaId;
    
    
    
	public MovimientosFilter(Long id, Date fecha, Double cantidad, TipoMovimiento tipo, Long categoriaId, Long cuentaId,
			Long tarjetaId) {
		super();
		this.id = id;
		this.fecha = fecha;
		this.cantidad = cantidad;
		this.tipo = tipo;
		this.categoriaId = categoriaId;
		this.cuentaId = cuentaId;
		this.tarjetaId = tarjetaId;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public Long getCategoriaId() {
		return categoriaId;
	}
	public void setCategoriaId(Long categoriaId) {
		this.categoriaId = categoriaId;
	}
	public Long getCuentaId() {
		return cuentaId;
	}
	public void setCuentaId(Long cuentaId) {
		this.cuentaId = cuentaId;
	}
	public Long getTarjetaId() {
		return tarjetaId;
	}
	public void setTarjetaId(Long tarjetaId) {
		this.tarjetaId = tarjetaId;
	}

    

}
