package com.bcp.reto.jwtl;

import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.bcp.reto.entity.Usuario;
import com.bcp.reto.repository.UsuarioRepository;

@Service
public class MyUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UsuarioRepository repository;
	
    @Override
    public UserDetails loadUserByUsername(String nombre) throws UsernameNotFoundException {
    	
    	Usuario usuario = repository.findByNombreAndEstadoIsTrue(nombre);
    	
    	if(!ObjectUtils.isEmpty(usuario)) {
    		usuario.setUltimaSesion(new Date());
    		repository.save(usuario);
    	}
    	
        return new User(usuario.getNombre(), usuario.getClave(),
                new ArrayList<>());
    }
}