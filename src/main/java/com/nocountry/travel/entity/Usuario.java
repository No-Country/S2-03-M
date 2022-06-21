package com.nocountry.travel.entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Usuario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull
    @Column(unique = true)
    private String email;
    @NotNull
    @Column(columnDefinition = "TEXT")
    private String token;
    @NotNull
    private String password;
    @ManyToMany(fetch = FetchType.EAGER) //indexa todo
    @JoinTable(joinColumns = @JoinColumn(name = "usuario_id", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "rol_id", referencedColumnName = "id")) //Para tabla intermedia con idUsuario e idRol
    private Set<Rol> roles = new HashSet<>();
    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "user")
    private List<Flight> flights= new ArrayList<>();
    public Usuario() {
    }
    
    public Usuario(@NotNull String email, @NotNull String token, @NotNull String password) {
        this.email = email;
        this.token = token;
        this.password = password;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }
    public Set<Rol> getRoles() {
        return roles;
    }
    public void setRoles(Set<Rol> roles) {
        this.roles = roles;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void addFlight(Flight flight){
        flights.add(flight);
    }
    public void removeFlight(Flight flight){
        flights.remove(flight);
    }
    
}
