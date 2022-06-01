package com.nocountry.travel.security;

import com.nocountry.travel.entity.Usuario;
import com.nocountry.travel.service.UsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserDetailsServiceImpl implements UserDetailsService{

    @Autowired
    UsuarioService usuarioService;


    public UserDetailsServiceImpl() {
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        try {
            Usuario usuario = usuarioService.getByEmail(email).get();
            
            if (usuario == null) {
                throw new UsernameNotFoundException("Email no encontrado");
            }
            
            return UsuarioPrincipalFactory.build(usuario);

        } catch (Exception e) {
            e.getMessage();
            return null;
        }
    }

    
    
}
