package com.bcp.reto.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bcp.reto.dto.MonedaRequestDto;
import com.bcp.reto.dto.MonedaResponseDto;
import com.bcp.reto.dto.UsuarioRequestDto;
import com.bcp.reto.service.MonedaService;

import io.reactivex.Completable;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

@RestController
@RequestMapping(value = "/api/moneda")
public class MonedaRestController {
	
	private MonedaService monedaService;
	
	@Autowired
	public MonedaRestController(MonedaService monedaService) {
		this.monedaService = monedaService;
	}
	
	@GetMapping
	public Single<ResponseEntity<List<MonedaResponseDto>>> listarMonedas() {
	    return monedaService.listarMonedas()
	            .subscribeOn(Schedulers.io())
	            .map(ResponseEntity::ok);
	}
	
	@GetMapping(value = "/{id}")
    public Single<ResponseEntity<MonedaResponseDto>> buscarPorId(@PathVariable(value = "id") Long id) {
        return monedaService.buscarPorId(id)
                .subscribeOn(Schedulers.io())
                .map(ResponseEntity::ok);
    }

    @DeleteMapping(value = "/{id}")
    public Completable eliminarMoneda(@PathVariable(value = "id") Long id) {
        return monedaService.eliminarMoneda(id)
                .subscribeOn(Schedulers.io());
    }
    
    @PutMapping(value = "/{id}")
    public Single<ResponseEntity<MonedaResponseDto>> actualizar(@PathVariable(value = "id") Long id,
                                                              @RequestBody MonedaRequestDto monedaRequestDto
                                                              ,HttpSession session) {
    		   setAuditoria(monedaRequestDto,session);
        return monedaService.actualizar(id, monedaRequestDto)
                .subscribeOn(Schedulers.io())
                .map(ResponseEntity::ok);
    }
    
    @PostMapping
    public Single<ResponseEntity<MonedaResponseDto>> guardar(
        @RequestBody MonedaRequestDto monedaRequestDto,HttpSession session) {
    			setAuditoria(monedaRequestDto,session);
        return monedaService.guardar(monedaRequestDto).subscribeOn(Schedulers.io())
                .map(ResponseEntity::ok);
    }
	
    public void setAuditoria(MonedaRequestDto monedaRequestDto
            , HttpSession session) {
		String usuario = (String) session.getAttribute("SESSION_USUARIO");
		monedaRequestDto.setUsuario_creacion(usuario);
		monedaRequestDto.setUsuario_actualizacion(usuario);
		monedaRequestDto.setFecha_creacion(new Date());
		monedaRequestDto.setFecha_actualizacion(new Date());
	}
}
