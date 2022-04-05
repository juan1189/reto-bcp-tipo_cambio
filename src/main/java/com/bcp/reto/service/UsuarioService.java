package com.bcp.reto.service;

import java.util.List;

import com.bcp.reto.dto.UsuarioRequestDto;
import com.bcp.reto.dto.UsuarioResponseDto;

import io.reactivex.Single;

public interface UsuarioService {
	
	Single<List<UsuarioResponseDto>> listarUsuario();
	
	Single<UsuarioResponseDto> guardar(UsuarioRequestDto usuarioRequestDto);
}
