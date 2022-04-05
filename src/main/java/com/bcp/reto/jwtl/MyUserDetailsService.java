package com.bcp.reto.jwtl;

import java.security.Permission;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.bcp.reto.entity.Usuario;
import com.bcp.reto.enums.EnumRol;
import com.bcp.reto.repository.UsuarioRepository;

@Service
public class MyUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UsuarioRepository repository;
	@Enumerated(EnumType.STRING)
	private EnumRol rol;
	
    @Override
    public UserDetails loadUserByUsername(String nombre) throws UsernameNotFoundException {
    	
    	Usuario usuario = repository.findByNombreAndEstadoIsTrue(nombre);    	    	
         
    	if(!ObjectUtils.isEmpty(usuario)) {
    		List<GrantedAuthority> roles = new ArrayList<>();
            List<GrantedAuthority> grantedAuthorities = new ArrayList <>();
//            for (Permission permission : permissions) {
//                if (permission != null && permission.getName()!=null) {
// 
//                GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(rol.toString());
//                roles.add(grantedAuthority);
                //1: Aquí, la información de autorización se agrega al objeto GrantedAuthority y el objeto GrantedAuthority se utilizará para la verificación de autorización completa más adelante.
//                grantedAuthorities.add(grantedAuthority);
//                }
//            }
                return new User(usuario.getNombre(), usuario.getClave(), new ArrayList<>());
        } else {
        	throw new UsernameNotFoundException("Admin: " + nombre + " no existe!");
        }

    	
//    	if(!ObjectUtils.isEmpty(usuario)) {
//    		usuario.setUltimaSesion(new Date());
//    		repository.save(usuario);
//    		return new User(usuario.getNombre(), usuario.getClave(),
//                    new ArrayList<>());
//    	}else {
//    		throw new UsernameNotFoundException("Admin: " + nombre + " no existe!");
//    	}
    	
        
    }
}