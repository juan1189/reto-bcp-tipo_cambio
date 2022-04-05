package com.bcp.reto.transform.entity;

import java.util.Date;
import java.util.function.Function;

import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import com.bcp.reto.dto.UsuarioRequestDto;
import com.bcp.reto.entity.Usuario;

@Component
public class UsuarioTransformToEntity implements Function<UsuarioRequestDto,Usuario>{

	@Override
	public Usuario apply(UsuarioRequestDto t) {
		if(!ObjectUtils.isEmpty(t)) {				
			return Usuario.builder()
			.nombre(t.getNombre())
			.clave(t.getClave())
			.estado(true)
			.fecha_creacion(new Date())
			.fecha_actualizacion(new Date())	
			.usuario_actualizacion(t.getUsuario_actualizacion())
			.usuario_creacion(t.getUsuario_creacion())
			.build();
		}		
		return null;
	}

}
