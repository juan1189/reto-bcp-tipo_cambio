package com.bcp.reto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

@SpringBootApplication
public class CambioTipoMonedaBackApplication {

	public static void main(String[] args) {
		SpringApplication.run(CambioTipoMonedaBackApplication.class, args);
	}

}
