package com.bcp.reto.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.MappedSuperclass;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@MappedSuperclass
public class Auditoria implements Serializable{

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha_creacion;
    
    @Column(length = 20)
    private String usuario_creacion;
    
    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha_actualizacion;
    
    @Column(length = 20)
    private String usuario_actualizacion;
}
