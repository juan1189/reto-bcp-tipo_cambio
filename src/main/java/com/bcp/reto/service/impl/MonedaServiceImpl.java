package com.bcp.reto.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bcp.reto.dto.MonedaRequestDto;
import com.bcp.reto.dto.MonedaResponseDto;
import com.bcp.reto.entity.Moneda;
import com.bcp.reto.enums.ErrorCode;
import com.bcp.reto.exception.ServiceException;
import com.bcp.reto.repository.MonedaRepository;
import com.bcp.reto.service.MonedaService;
import com.bcp.reto.transform.dto.MonedaTransformToDto;
import com.bcp.reto.transform.entity.MonedaTransformToEntity;

import io.reactivex.Completable;
import io.reactivex.Single;

@Service
public class MonedaServiceImpl implements MonedaService {

	private MonedaRepository monedaRepository;
	private MonedaTransformToDto monedaTransformToDto;
	private MonedaTransformToEntity monedaTransformToEntity;
	
	@Autowired
	public MonedaServiceImpl(MonedaRepository monedaRepository,
			MonedaTransformToDto monedaTransformToDto,
			MonedaTransformToEntity monedaTransformToEntity) {
		this.monedaRepository = monedaRepository;
		this.monedaTransformToDto = monedaTransformToDto;
		this.monedaTransformToEntity = monedaTransformToEntity;
	}

	
	@Override
	public Single<List<MonedaResponseDto>> listarMonedas() {
		 return Single.create(source ->{
				try {
					source.onSuccess(monedaRepository.findAll()
							.stream()
							.map(monedaTransformToDto)
							.collect(Collectors.toList()));								
				} catch (Exception e) {
					source.onError(new ServiceException(ErrorCode.E000, e.getMessage()));
				}
	 		});
	}


	@Override
	public Single<MonedaResponseDto> buscarPorId(Long id) {
		 return Single.create(singleSubscriber -> {
	            try {
	                Optional<Moneda> optMoneda = monedaRepository.findByIdAndEstadoIsTrue(id);
	                optMoneda.ifPresentOrElse(m -> singleSubscriber.onSuccess(monedaTransformToDto.apply(m)),
	                        () -> singleSubscriber.onError(new ServiceException(ErrorCode.E001)));
	            } catch (Exception ex) {
	                singleSubscriber.onError(new ServiceException(ErrorCode.E000, ex.getMessage()));
	            }
	        });
	}


	@Override
	public Completable eliminarMoneda(Long id) {
		 return Completable.create(completableSubscriber -> {
	            try {
	            	monedaRepository.findByIdAndEstadoIsTrue(id).ifPresentOrElse(
	                        entity -> {
	                        	monedaRepository.delete(id);
	                            completableSubscriber.onComplete();
	                        }, () -> {
	                            throw new ServiceException(ErrorCode.E001);
	                        }
	                );
	            } catch (Exception ex) {
	                completableSubscriber.onError(new ServiceException(ErrorCode.E000, ex.getMessage()));
	            }
	        });
	}


	@Override
	public Single<MonedaResponseDto> actualizar(Long id, MonedaRequestDto monedaRequestDto) {
		 return Single.create(singleSubscriber -> {
	            try {
	            	monedaRepository.findByIdAndEstadoIsTrue(id).ifPresentOrElse(
	                        entity -> {
	                            if (monedaRequestDto.getMonedaNacional() &&
	                                    !entity.getMonedaNacional() &&
	                                    monedaRepository.findByMonedaNacionalIsTrueAndEstadoIsTrue().isPresent()) {
	                                throw new ServiceException(ErrorCode.E003);
	                            }
	                            if (monedaRequestDto.getMonedaNacional() &&
	                            		monedaRequestDto.getValorCambio().compareTo(BigDecimal.ONE) != 0) {
	                                throw new ServiceException(ErrorCode.E002);
	                            }
	                            entity.setCodigo(monedaRequestDto.getCodigo());
	                            entity.setNombre(monedaRequestDto.getNombre());
	                            entity.setValorCambio(monedaRequestDto.getValorCambio());
	                            entity.setMonedaNacional(monedaRequestDto.getMonedaNacional());
	                            entity.setFecha_actualizacion(monedaRequestDto.getFecha_actualizacion());
	                            entity.setUsuario_actualizacion(monedaRequestDto.getUsuario_actualizacion());
	                            MonedaResponseDto responseDto = monedaTransformToDto.apply(monedaRepository.save(entity));
	                            singleSubscriber.onSuccess(responseDto);

	                        }, () -> {
	                            throw new ServiceException(ErrorCode.E001);
	                        });
	            } catch (Exception ex) {
	            	 if (ex instanceof ServiceException) {
	                     singleSubscriber.onError(ex);
	                 } else {
	                     singleSubscriber.onError(new ServiceException(ErrorCode.E000, ex.getMessage()));
	                 }
	            }
	        });
	}


	@Override
	public Single<MonedaResponseDto> guardar(MonedaRequestDto monedaRequestDto) {
		 return Single.create(singleSubscriber -> {
	            try {
	                Moneda entity = monedaTransformToEntity.apply(monedaRequestDto);
	                if (entity.getMonedaNacional()
	                        && entity.getValorCambio().compareTo(BigDecimal.ONE) != 0) {
	                    throw new ServiceException(ErrorCode.E002);
	                }
	                if (entity.getMonedaNacional() && monedaRepository.findByMonedaNacionalIsTrueAndEstadoIsTrue().isPresent()) {
	                    throw new ServiceException(ErrorCode.E003);
	                }
	                if (monedaRepository.findByCodigoAndEstadoIsTrue(entity.getCodigo()).isPresent()) {
	                    throw new ServiceException(ErrorCode.E004);
	                }
	                entity.setEstado(true);
	                MonedaResponseDto responseDto = monedaTransformToDto.apply(monedaRepository.save(entity));
	                singleSubscriber.onSuccess(responseDto);
	            } catch (Exception ex) {
	            	 if (ex instanceof ServiceException) {
	                     singleSubscriber.onError(ex);
	                 } else {
	                     singleSubscriber.onError(new ServiceException(ErrorCode.E000, ex.getMessage()));
	                 }
	            }
	        });
	}

}
