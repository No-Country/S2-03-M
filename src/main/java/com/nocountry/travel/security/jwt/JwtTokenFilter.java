package com.nocountry.travel.security.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nocountry.travel.security.UserDetailsServiceImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

public class JwtTokenFilter extends OncePerRequestFilter{

    private final static Logger logger = LoggerFactory.getLogger(JwtTokenFilter.class);

    @Autowired
    JwtProvider jwtProvider;

    @Autowired
    UserDetailsServiceImpl userDetailsServiceImpl;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        
                try {
                    
                    String token = getToken(request);
                    String email = jwtProvider.getEmailFromToken(token);

                    UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername(email);

                    UsernamePasswordAuthenticationToken auth =
                            new UsernamePasswordAuthenticationToken(email, null, userDetails.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(auth);

                } catch (Exception e) {
                    logger.error("Error en el metodo doFilter");
                }
                
                filterChain.doFilter(request, response);
    }

    private String getToken(HttpServletRequest req){   //Elimino la palabra Bearer y el espacio para dejar el token solo
        String authReq = req.getHeader("Authorization"); 
        if (authReq != null && authReq.startsWith("Bearer ") ) {
            return authReq.replace("Bearer ", "");
        }
        return null;
    }
    
    

}
