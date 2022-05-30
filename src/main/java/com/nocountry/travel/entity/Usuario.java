package com.nocountry.travel.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.ManyToAny;

@Entity
public class Usuario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull
    @Column(unique = true)
    private String mail;
    @NotNull
    private String password;
    @ManyToMany(fetch = FetchType.EAGER) //indexa todo
    @JoinTable(joinColumns = @JoinColumn(name = "usuario_id", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "rol_id", referencedColumnName = "id")) //Para tabla intermedia con idUsuario e idRol
    private Set<Rol> roles = new HashSet<>();
    public Usuario() {
    }
    public Usuario(@NotNull String mail, @NotNull String password) {
        this.mail = mail;
        this.password = password;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getMail() {
        return mail;
    }
    public void setMail(String mail) {
        this.mail = mail;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public Set<Rol> getRoles() {
        return roles;
    }
    public void setRoles(Set<Rol> roles) {
        this.roles = roles;
    }

    
}
