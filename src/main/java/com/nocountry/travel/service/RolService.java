package com.nocountry.travel.service;

import com.nocountry.travel.entity.Rol;
import com.nocountry.travel.enums.RolNombre;
import com.nocountry.travel.repository.RolRepository;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RolService {
    @Autowired
    RolRepository rolRepository;

    public Optional<Rol> getByRolNombre(RolNombre rolNombre) throws Exception{
        if (rolRepository.findByRolNombre(rolNombre).isPresent()) {
            return rolRepository.findByRolNombre(rolNombre);
        }else{
            Rol rol = new Rol(RolNombre.ROLE_USER);
            save(rol);
            return rolRepository.findByRolNombre(rolNombre);
        }
    }

    public boolean existsByRolNombre(RolNombre  rolNombre){
        return rolRepository.existsByRolNombre(rolNombre);
    }

    public void save (Rol rol){
        rolRepository.save(rol);
    }
}
