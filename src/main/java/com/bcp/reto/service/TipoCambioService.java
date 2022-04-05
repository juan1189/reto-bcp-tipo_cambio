package com.bcp.reto.service;

import org.springframework.http.ResponseEntity;

import com.bcp.reto.dto.TipoCambioRequestDto;
import com.bcp.reto.dto.TipoCambioResponseDto;

import io.reactivex.Single;

public interface TipoCambioService {

	Single<TipoCambioResponseDto> cambioMoneda(TipoCambioRequestDto tipoCambioRequestDto);
}
