package com.nocountry.travel.repository;

import com.nocountry.travel.entity.Usuario;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{
    
    Optional<Usuario> findByEmail(String email);
    boolean existsByEmail(String email);

    @Query("SELECT u FROM Usuario u WHERE u.token = :token")
    public String getEmailByToken(@Param("token") String token);


}
