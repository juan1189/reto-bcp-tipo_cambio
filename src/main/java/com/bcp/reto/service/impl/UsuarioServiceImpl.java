package com.bcp.reto.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bcp.reto.dto.UsuarioRequestDto;
import com.bcp.reto.dto.UsuarioResponseDto;
import com.bcp.reto.enums.ErrorCode;
import com.bcp.reto.exception.ServiceException;
import com.bcp.reto.repository.UsuarioRepository;
import com.bcp.reto.service.UsuarioService;
import com.bcp.reto.transform.dto.UsuarioTransformToDto;
import com.bcp.reto.transform.entity.UsuarioTransformToEntity;

import io.reactivex.Single;

@Service
public class UsuarioServiceImpl implements UsuarioService{

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private UsuarioTransformToDto usuarioTransformToDto;
	
	@Autowired
	private UsuarioTransformToEntity usuarioTransformToEntity;
	
	@Override
	public Single<List<UsuarioResponseDto>> listarUsuario() {
		return Single.create(singleSubscribe -> {
			try {
				singleSubscribe.onSuccess(usuarioRepository.findAll().stream()
						.map(usuarioTransformToDto)
						.collect(Collectors.toList()));				
			} catch (Exception e) {
				singleSubscribe.onError(new ServiceException(ErrorCode.E000,e.getMessage()));
			}						
		});
	}

	@Override
	public Single<UsuarioResponseDto> guardar(UsuarioRequestDto usuarioRequestDto) {
		return Single.create(singleSubscribe -> {
			try {
				singleSubscribe.onSuccess(usuarioTransformToDto.apply(usuarioRepository.save(usuarioTransformToEntity.apply(usuarioRequestDto))));
			} catch (Exception e) {
				singleSubscribe.onError(new ServiceException(ErrorCode.E000,e.getMessage()));
			}
		});
	}

}
