package com.bcp.reto.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bcp.reto.dto.TipoCambioRequestDto;
import com.bcp.reto.dto.TipoCambioResponseDto;
import com.bcp.reto.entity.Moneda;
import com.bcp.reto.entity.TipoCambio;
import com.bcp.reto.enums.ErrorCode;
import com.bcp.reto.exception.ServiceException;
import com.bcp.reto.repository.MonedaRepository;
import com.bcp.reto.repository.TipoCambioRepository;
import com.bcp.reto.service.TipoCambioService;
import com.bcp.reto.transform.dto.TipoCambioResponseTransformToDto;

import io.reactivex.Single;

@Service
public class TipoCambioServiceImpl implements TipoCambioService{
	
	private final Integer numeroDecimales = 4;
	 
	private MonedaRepository monedaRepository;
	private TipoCambioResponseTransformToDto tipoCambioResponseTransformToDto;
	private TipoCambioRepository tipoCambioRepository;
	
	@Autowired
	public TipoCambioServiceImpl(MonedaRepository monedaRepository
						,TipoCambioRepository tipoCambioRepository
						,TipoCambioResponseTransformToDto tipoCambioResponseTransformToDto) {
		this.monedaRepository = monedaRepository;
		this.tipoCambioRepository = tipoCambioRepository;
		this.tipoCambioResponseTransformToDto = tipoCambioResponseTransformToDto;
	}
	
	@Override
	public Single<TipoCambioResponseDto> cambioMoneda(TipoCambioRequestDto tipoCambioRequestDto) {
		
		return Single.create(source -> {
			try {
				List<Moneda> monedas = monedaRepository
						.buscarMonedasPorTiposDeCambio(tipoCambioRequestDto.getCodigoMonedaOrigen()
													, tipoCambioRequestDto.getCodigoMonedaDestino());
				monedas.stream()
					   .filter(Moneda::getMonedaNacional)
					   .findFirst()
					   .orElseThrow(() -> new ServiceException(ErrorCode.E005));
				
				Moneda monedaOrigen = monedas.stream()
					   .filter(m -> m.getCodigo().equalsIgnoreCase(tipoCambioRequestDto.getCodigoMonedaOrigen()) &&  m.getValorCambio() != null)
					   .findFirst()
					   .orElseThrow(() -> new ServiceException(ErrorCode.E006, tipoCambioRequestDto.getCodigoMonedaOrigen()));
				
				Moneda monedaDestino = monedas.stream()
						.filter(m -> m.getCodigo().equalsIgnoreCase(tipoCambioRequestDto.getCodigoMonedaDestino()) && m.getValorCambio() != null)
						.findFirst()
						.orElseThrow(() -> new ServiceException(ErrorCode.E007,tipoCambioRequestDto.getCodigoMonedaDestino()));
				
				BigDecimal montoMonedaNacional = tipoCambioRequestDto.getMonto().multiply(monedaOrigen.getValorCambio());
				BigDecimal montoTipoCambio = montoMonedaNacional.divide(monedaDestino.getValorCambio(),numeroDecimales,RoundingMode.HALF_UP);
				
				TipoCambio tipoCambio = TipoCambio.builder()
										.monto(tipoCambioRequestDto.getMonto())
										.codigoMonedaDestino(tipoCambioRequestDto.getCodigoMonedaDestino())
										.codigoMonedaOrigen(tipoCambioRequestDto.getCodigoMonedaOrigen())
										.tipoCambio(monedaDestino.getMonedaNacional()?monedaOrigen.getValorCambio():monedaDestino.getValorCambio())
										.montoTipoCambio(montoTipoCambio)
										.usuario_actualizacion(tipoCambioRequestDto.getUsuario_actualizacion())
										.usuario_creacion(tipoCambioRequestDto.getUsuario_creacion())
										.fecha_actualizacion(tipoCambioRequestDto.getFecha_actualizacion())
										.fecha_creacion(tipoCambioRequestDto.getFecha_creacion())
										.build();
				
				tipoCambioRepository.save(tipoCambio);				
				source.onSuccess(tipoCambioResponseTransformToDto.apply(tipoCambio));
				
			} catch (Exception ex) {
				if (ex instanceof ServiceException) {
					source.onError(ex);
              } else {
            	  source.onError(new ServiceException(ErrorCode.E000, ex.getMessage()));
              }
			}
		});
	}

}
