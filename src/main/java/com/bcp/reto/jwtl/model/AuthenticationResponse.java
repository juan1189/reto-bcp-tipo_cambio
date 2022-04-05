package com.bcp.reto.jwtl.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class AuthenticationResponse implements Serializable {

	private static final long serialVersionUID = 1L;
	private final String jwt;
 
}
