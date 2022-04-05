package com.bcp.reto.transform.entity;

import java.util.function.Function;

import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import com.bcp.reto.dto.MonedaRequestDto;
import com.bcp.reto.entity.Moneda;

@Component
public class MonedaTransformToEntity implements Function<MonedaRequestDto,Moneda>{

	@Override
	public Moneda apply(MonedaRequestDto t) {
		if(!ObjectUtils.isEmpty(t)) {				
			return Moneda.builder()
			.codigo(t.getCodigo())
			.nombre(t.getNombre())
			.monedaNacional(t.getMonedaNacional())
			.valorCambio(t.getValorCambio())
			.fecha_creacion(t.getFecha_creacion())
			.fecha_actualizacion(t.getFecha_actualizacion())
			.usuario_creacion(t.getUsuario_creacion())
			.usuario_actualizacion(t.getUsuario_actualizacion())
			.build();
		}		
		return null;
	}

}
