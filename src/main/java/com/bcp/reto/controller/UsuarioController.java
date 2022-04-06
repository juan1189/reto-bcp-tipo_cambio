package com.bcp.reto.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bcp.reto.dto.TipoCambioRequestDto;
import com.bcp.reto.dto.UsuarioRequestDto;
import com.bcp.reto.dto.UsuarioResponseDto;
import com.bcp.reto.service.UsuarioService;

import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

@RestController
@RequestMapping(value = "/api/usuario")
public class UsuarioController {

	
	private final UsuarioService usuarioService;
	
	@Autowired
	public UsuarioController(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}

	@GetMapping
	public Single<ResponseEntity<List<UsuarioResponseDto>>> listarUsuario() {
	    return usuarioService.listarUsuario()
	            .subscribeOn(Schedulers.io())
	            .map(ResponseEntity::ok);
	}
	
	@PostMapping
    public Single<ResponseEntity<UsuarioResponseDto>> guardar(
        @RequestBody UsuarioRequestDto usuarioRequestDto, HttpSession session) {
				setAuditoria(usuarioRequestDto,session);
        return usuarioService.guardar(usuarioRequestDto).subscribeOn(Schedulers.io())
                .map(ResponseEntity::ok);
    }
	
	public void setAuditoria(UsuarioRequestDto usuarioRequestDto
            , HttpSession session) {
		String usuario = (String) session.getAttribute("SESSION_USUARIO");
		usuarioRequestDto.setUsuario_creacion(usuario);
		usuarioRequestDto.setUsuario_actualizacion(usuario);
		usuarioRequestDto.setFecha_creacion(new Date());
		usuarioRequestDto.setFecha_actualizacion(new Date());
	}
}
