package com.nocountry.travel.security.jwt;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nocountry.travel.entity.Rol;
import com.nocountry.travel.entity.Usuario;
import com.nocountry.travel.enums.RolNombre;
import com.nocountry.travel.security.UserDetailsServiceImpl;
import com.nocountry.travel.service.RolService;
import com.nocountry.travel.service.UsuarioService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.filter.OncePerRequestFilter;

public class JwtTokenFilter extends OncePerRequestFilter{

    private final static Logger logger = LoggerFactory.getLogger(JwtTokenFilter.class);

    @Autowired
    JwtProvider jwtProvider;

    @Autowired
    UserDetailsServiceImpl userDetailsServiceImpl;

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    RolService rolService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Value("${secretPsw}")
    String secretPsw;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        
                try {
                    System.out.println(request);
                    String token = getToken(request);
                    String email = usuarioService.getEmailByToken(token);
                    // String email = jwtProvider.getEmailFromToken(token); 
                    if(email == null && !usuarioService.existsEmail(getEmail(request))){
                        email = getEmail(request);
                        String encodedpass = passwordEncoder.encode(secretPsw);
                        System.out.println(encodedpass);
                        Usuario usuario = new Usuario(email, token, encodedpass);
                        Rol rolUser = rolService.getByRolNombre(RolNombre.ROLE_USER).get();
                        Set<Rol> roles = new HashSet<>();
                        roles.add(rolUser);
                        usuario.setRoles(roles);
                        usuarioService.save(usuario);
                    }else{
                        email = getEmail(request);
                    }
                    System.out.println(email);

                    UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername(email);

                    UsernamePasswordAuthenticationToken auth =
                            new UsernamePasswordAuthenticationToken(email, null, userDetails.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(auth);

                } catch (Exception e) {
                    logger.error("Error en el metodo doFilter");
                    System.out.println(e.getMessage());
                }
                
                filterChain.doFilter(request, response);
    }

    private String getToken(HttpServletRequest req){   //Elimino la palabra Bearer y el espacio para dejar el token solo
        String authReq = req.getHeader("Authorization");
        // System.out.println(authReq);
        authReq = authReq.replace("{\"value\":\"", "");
        authReq = authReq.replace("\"}", "");
        if (authReq != null && authReq.startsWith("Bearer ") ) {

            String aux = authReq.replace("Bearer ", "");

            return aux;
        }
        return null;
    }

    private String getEmail(HttpServletRequest req){   //Elimino la palabra Bearer y el espacio para dejar el token solo
        String authReq = req.getHeader("email");
        // System.out.println(authReq);
        
        return authReq;
    }
    
    

}
