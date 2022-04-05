package com.bcp.reto.transform.dto;

import java.util.function.Function;

import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import com.bcp.reto.dto.TipoCambioResponseDto;
import com.bcp.reto.entity.TipoCambio;

@Component
public class TipoCambioResponseTransformToDto implements Function<TipoCambio,TipoCambioResponseDto>{

	@Override
	public TipoCambioResponseDto apply(TipoCambio t) {
		if(!ObjectUtils.isEmpty(t)) {	
			return TipoCambioResponseDto.builder()
					.codigoMonedaDestino(t.getCodigoMonedaDestino())
					.codigoMonedaOrigen(t.getCodigoMonedaOrigen())
					.monto(t.getMonto())
					.montoTipoCambio(t.getMontoTipoCambio())
					.tipoCambio(t.getTipoCambio())
					.build();
		}
		return null;
	}

}
