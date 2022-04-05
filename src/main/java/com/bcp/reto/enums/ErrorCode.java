package com.bcp.reto.enums;

import com.bcp.reto.exception.ApiError;
import lombok.Getter;

@Getter
public enum ErrorCode {

    E000("E000","Error desconocido"),
    E001("E001","Moneda no encontrada"),
    E002("E002","Moneda nacional debe tener tipo de cambio (1)"),
    E003("E003","Ya existe registrada una moneda nacional"),
    E004("E004","El código de moneda ya esta registrado"),
    E005("E005","Configure moneda nacional"),
    E006("E006","Moneda origen no configurada"),
    E007("E007","Moneda destino no configurada"),
    E008("E008","Servicio no tiene data"),
    E009("E009","Usuario o clave incorrecto");

    String codigo;
    String mensaje;

    ErrorCode(String codigo, String mensaje){
        this.codigo = codigo;
        this.mensaje=mensaje;
    }

    public ApiError getApiError() {
        return ApiError.builder()
                .codigo(this.codigo)
                .mensaje(this.mensaje)
                .build();
    }

}
