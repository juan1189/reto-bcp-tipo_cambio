package com.bcp.reto.service;

import java.util.List;

import com.bcp.reto.dto.MonedaRequestDto;
import com.bcp.reto.dto.MonedaResponseDto;

import io.reactivex.Completable;
import io.reactivex.Single;

public interface MonedaService {

	Single<List<MonedaResponseDto>> listarMonedas();
	
	Single<MonedaResponseDto> buscarPorId(Long id);
	
	Completable eliminarMoneda(Long id);
	
	Single<MonedaResponseDto> actualizar(Long id, MonedaRequestDto monedaRequestDto);
	
	Single<MonedaResponseDto> guardar(MonedaRequestDto monedaRequestDto);
}
