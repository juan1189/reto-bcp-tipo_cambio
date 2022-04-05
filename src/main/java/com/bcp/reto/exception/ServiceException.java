package com.bcp.reto.exception;

import com.bcp.reto.enums.ErrorCode;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ServiceException extends RuntimeException{
    private ApiError error;

    public ServiceException(ErrorCode errorCode) {
        super(errorCode.getMensaje());
        this.error = errorCode.getApiError();
    }

    public ServiceException(ErrorCode errorCode, String detail) {
        super(errorCode.getMensaje());
        this.error = errorCode.getApiError();
        this.error.setDetalle(detail);
    }

}
