package com.bcp.reto.transform.dto;

import java.util.function.Function;

import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import com.bcp.reto.dto.UsuarioResponseDto;
import com.bcp.reto.entity.Usuario;

@Component
public class UsuarioTransformToDto implements Function<Usuario, UsuarioResponseDto> {

	@Override
	public UsuarioResponseDto apply(Usuario t) {
		if(!ObjectUtils.isEmpty(t)) {				
			return UsuarioResponseDto.builder()
			.id(t.getId())
			.nombre(t.getNombre())
			.estado(t.getEstado())
			.ultimaSession(t.getUltimaSesion())
			.build();
		}		
		return null;
	}

}
