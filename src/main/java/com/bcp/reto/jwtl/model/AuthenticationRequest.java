package com.bcp.reto.jwtl.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthenticationRequest implements Serializable {

	private static final long serialVersionUID = 1L;
	private String username;
    private String password;

}
