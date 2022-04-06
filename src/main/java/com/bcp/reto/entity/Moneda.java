package com.bcp.reto.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Setter
@Getter
@NoArgsConstructor
@ToString
@Table(name="tbl_moneda")
public class Moneda extends Auditoria implements Serializable {
				
		@Builder
	    public Moneda(Date fecha_creacion, String usuario_creacion, Date fecha_actualizacion, String usuario_actualizacion,
			Long id, String codigo, String nombre, Boolean monedaNacional, BigDecimal valorCambio, Boolean estado) {
		super(fecha_creacion, usuario_creacion, fecha_actualizacion, usuario_actualizacion);
		this.id = id;
		this.codigo = codigo;
		this.nombre = nombre;
		this.monedaNacional = monedaNacional;
		this.valorCambio = valorCambio;
		this.estado = estado;
	}

		@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    @Column(length = 10)
	    private String codigo;

	    @Column(length = 10)
	    private String nombre;

	    @Column
	    private Boolean monedaNacional;

	    @Column(precision = 12, scale = 4)
	    private BigDecimal valorCambio;

	    @Column
	    private Boolean estado;
	    	    
}
