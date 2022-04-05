package com.bcp.reto.controller;

import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bcp.reto.dto.TipoCambioRequestDto;
import com.bcp.reto.dto.TipoCambioResponseDto;
import com.bcp.reto.service.TipoCambioService;

import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

@RestController
@RequestMapping(value = "/api/tipoCambio")
public class TipoCambioRestController {

	private TipoCambioService tipoCambioService;
	
	@Autowired
	public TipoCambioRestController(TipoCambioService tipoCambioService) {
		this.tipoCambioService= tipoCambioService;
	}
	
	@PostMapping
    public Single<ResponseEntity<TipoCambioResponseDto>> cambioMoneda(
            @RequestBody TipoCambioRequestDto tipoCambioRequestDto
            , HttpSession session) {
				setAuditoria(tipoCambioRequestDto,session);
        return tipoCambioService.cambioMoneda(tipoCambioRequestDto).subscribeOn(Schedulers.io())
                .map(ResponseEntity::ok);
    }
	
	public void setAuditoria(TipoCambioRequestDto tipoCambioRequestDto
            , HttpSession session) {
		String usuario = (String) session.getAttribute("SESSION_USUARIO");
		tipoCambioRequestDto.setUsuario_creacion(usuario);
		tipoCambioRequestDto.setUsuario_actualizacion(usuario);
		tipoCambioRequestDto.setFecha_creacion(new Date());
		tipoCambioRequestDto.setFecha_actualizacion(new Date());
	}
}
