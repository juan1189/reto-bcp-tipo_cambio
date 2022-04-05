package com.bcp.reto.entity;

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
@Table(name="tbl_tipo_cambio")
public class TipoCambio extends Auditoria{
	
	@Builder
	public TipoCambio(Date fecha_creacion, String usuario_creacion, Date fecha_actualizacion,
			String usuario_actualizacion, Long id, String codigoMonedaOrigen, String codigoMonedaDestino,
			BigDecimal monto, BigDecimal tipoCambio, BigDecimal montoTipoCambio) {
		super(fecha_creacion, usuario_creacion, fecha_actualizacion, usuario_actualizacion);
		this.id = id;
		this.codigoMonedaOrigen = codigoMonedaOrigen;
		this.codigoMonedaDestino = codigoMonedaDestino;
		this.monto = monto;
		this.tipoCambio = tipoCambio;
		this.montoTipoCambio = montoTipoCambio;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(length = 10)
	private String codigoMonedaOrigen;
	
	@Column(length = 10)
	private String codigoMonedaDestino;
	
	@Column(precision = 12, scale = 4)
	private BigDecimal monto;
	
	@Column(precision = 12, scale = 4)
	private BigDecimal tipoCambio;
	
	@Column(precision = 12, scale = 4)
	private BigDecimal montoTipoCambio;
	
}
