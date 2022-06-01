package com.nocountry.travel.service;

import com.google.common.base.Optional;
import com.nocountry.travel.entity.Rol;
import com.nocountry.travel.enums.RolNombre;
import com.nocountry.travel.repository.RolRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RolService {
    @Autowired
    RolRepository rolRepository;

    public Optional<Rol> getByRolNombre(RolNombre rolNombre){
        return rolRepository.getByRolNombre(rolNombre);
    }

    public boolean existsByRolNombre(RolNombre  rolNombre){
        return rolRepository.existsByRolNombre(rolNombre);
    }

    public void save (Rol rol){
        rolRepository.save(rol);
    }
}
