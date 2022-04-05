package com.bcp.reto.dto;

import java.io.Serializable;

import com.bcp.reto.entity.Auditoria;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UsuarioRequestDto extends Auditoria implements Serializable{
	
	private String nombre;
	private String clave;
}
