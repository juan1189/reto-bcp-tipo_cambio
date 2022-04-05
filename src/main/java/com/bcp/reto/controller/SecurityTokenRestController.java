package com.bcp.reto.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bcp.reto.enums.ErrorCode;
import com.bcp.reto.exception.ServiceException;
import com.bcp.reto.jwtl.MyUserDetailsService;
import com.bcp.reto.jwtl.model.AuthenticationRequest;
import com.bcp.reto.jwtl.model.AuthenticationResponse;
import com.bcp.reto.jwtl.util.JwtUtil;

import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

@RestController
@RequestMapping(value = "/api/reto/authenticate" )
public class SecurityTokenRestController {
	
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtUtil jwtTokenUtil;

	@Autowired
	private MyUserDetailsService userDetailsService;
	
	@PostMapping
	public Single<ResponseEntity<AuthenticationResponse>> crearToken(@RequestBody AuthenticationRequest authenticationRequest
																	, HttpServletRequest request){
		
		return this.validacionAutenticacion(authenticationRequest.getUsername(), authenticationRequest.getPassword(),request)			
				.subscribeOn(Schedulers.io())
				.map(ResponseEntity::ok);
	}
	
	public Single<AuthenticationResponse> validacionAutenticacion(String user, String pass, HttpServletRequest request){
		return Single.create(source -> {			
			try {
				authenticationManager.authenticate(
						new UsernamePasswordAuthenticationToken(user, pass)
				);
				
				final UserDetails userDetails = userDetailsService
						.loadUserByUsername(user);

				final String jwt = jwtTokenUtil.generateToken(userDetails);
				
				AuthenticationResponse a = new AuthenticationResponse(jwt);
				
				request.getSession().setAttribute("SESSION_USUARIO", user);
				
				source.onSuccess(a);
				
			}catch(BadCredentialsException b) {
				source.onError(new ServiceException(ErrorCode.E009,b.getMessage()));
			}catch (Exception e) {
				source.onError(new ServiceException(ErrorCode.E000,e.getMessage()));
			}								
	  	});
	}
}
