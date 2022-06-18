package com.nocountry.travel.security;

import java.util.List;
import java.util.stream.Collectors;

import com.nocountry.travel.entity.Usuario;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

public class UsuarioPrincipalFactory {

    public static UsuarioPrincipal build(Usuario usuario){
        List<GrantedAuthority> authorities =    //Se guardan los roles convertidos en authorities en la lista authorities
                usuario.getRoles().stream().map(rol -> new SimpleGrantedAuthority(rol.getRolNombre().name())).collect(Collectors.toList());

        return new UsuarioPrincipal(usuario.getEmail(), usuario.getPassword(), authorities);

    }
    
}
