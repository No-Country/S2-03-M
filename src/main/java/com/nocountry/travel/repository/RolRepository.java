package com.nocountry.travel.repository;

import com.nocountry.travel.entity.Rol;
import com.nocountry.travel.enums.RolNombre;

import java.lang.StackWalker.Option;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RolRepository extends JpaRepository<Rol, Integer>{
    
    Optional<Rol> findByRolNombre(RolNombre rolNombre);
    boolean existsByRolNombre(RolNombre rolNombre);

}
