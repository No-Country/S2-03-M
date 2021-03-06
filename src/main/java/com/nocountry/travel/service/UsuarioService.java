package com.nocountry.travel.service;

import java.util.Optional;

import javax.transaction.Transactional;

import com.nocountry.travel.entity.Usuario;
import com.nocountry.travel.repository.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UsuarioService {
    
    @Autowired
    UsuarioRepository usuarioRepository;

    public Optional<Usuario> getByEmail(String email){
        return usuarioRepository.findByEmail(email);
    }

    public boolean existsEmail(String email){
        return usuarioRepository.existsByEmail(email);
    }

    public Usuario save (Usuario usuario){
        return usuarioRepository.save(usuario);
    }

    public String getEmailByToken(String token){
        return usuarioRepository.getEmailByToken(token);
    }

}
