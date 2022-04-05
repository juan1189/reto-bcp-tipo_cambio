package com.bcp.reto.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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
@Table(name = "tbl_usuario")
public class Usuario extends Auditoria{
	
	@Builder
	public Usuario(Date fecha_creacion, String usuario_creacion, Date fecha_actualizacion, String usuario_actualizacion,
			Long id, String nombre, String clave, Date ultimaSesion,Boolean estado) {
		super(fecha_creacion, usuario_creacion, fecha_actualizacion, usuario_actualizacion);
		this.id = id;
		this.nombre = nombre;
		this.clave = clave;
		this.ultimaSesion = ultimaSesion;
		this.estado = estado;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(length = 20)
	private String nombre;
	
	@Column(length = 20)
	private String clave;
	
	@Column
	private Boolean estado;
	
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date ultimaSesion;
}
