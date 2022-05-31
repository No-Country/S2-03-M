package com.nocountry.travel.repository;

import com.google.common.base.Optional;
import com.nocountry.travel.entity.Rol;
import com.nocountry.travel.enums.RolNombre;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolRepository extends JpaRepository<Rol, Integer>{
    
    Optional<Rol> findByRolNombre(RolNombre rolNombre);
    boolean existsByRolNombre(RolNombre rolNombre);

}
