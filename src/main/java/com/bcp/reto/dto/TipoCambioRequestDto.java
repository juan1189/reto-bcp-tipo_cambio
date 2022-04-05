package com.bcp.reto.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import com.bcp.reto.entity.Auditoria;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TipoCambioRequestDto extends Auditoria implements Serializable {
	  private String codigoMonedaOrigen;
	  private String codigoMonedaDestino;
	  private BigDecimal monto;
}
