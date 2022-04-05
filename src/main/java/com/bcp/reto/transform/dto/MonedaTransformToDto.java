package com.bcp.reto.transform.dto;

import java.util.function.Function;

import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import com.bcp.reto.dto.MonedaResponseDto;
import com.bcp.reto.entity.Moneda;

@Component
public class MonedaTransformToDto implements Function<Moneda,MonedaResponseDto>{

	@Override
	public MonedaResponseDto apply(Moneda t) {
		if(!ObjectUtils.isEmpty(t)) {				
			return MonedaResponseDto.builder()
			.id(t.getId())
			.codigo(t.getCodigo())
			.nombre(t.getNombre())
			.monedaNacional(t.getMonedaNacional())
			.valorCambio(t.getValorCambio())
			.build();
		}		
		return null;
	}

}
