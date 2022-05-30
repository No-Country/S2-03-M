package com.nocountry.travel.repository;

import com.google.common.base.Optional;
import com.nocountry.travel.entity.Usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Usuariorepository extends JpaRepository<Usuario, Integer>{
    
    Optional<Usuario> findByEmail(String email);
    boolean existsByEmail(String email);

}
