package com.nocountry.travel.security.jwt;

import java.util.Date;

import com.nocountry.travel.security.RSA;
import com.nocountry.travel.security.UsuarioPrincipal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JwtProvider {//Crea los tokens y verifica que sean correctos
    
    
    private final static Logger logger = LoggerFactory.getLogger(JwtProvider.class);

    @Value("${jwt.secret}")
    String secret;

    @Value("${jwt.expiration}")
    int expiration;

    @Autowired
    private RSA rsa;

    public String generateToken(Authentication authentication){
        UsuarioPrincipal usuarioPrincipal = (UsuarioPrincipal) authentication.getPrincipal();
        return Jwts.builder().setSubject(usuarioPrincipal.getUsername())
            .setIssuedAt(new Date(System.currentTimeMillis()))
            .setExpiration(new Date(System.currentTimeMillis() + expiration))
            .signWith(SignatureAlgorithm.HS512, secret) //Algoritmo reversible
            .compact();
    }

    public String getEmailFromToken(String token){
        rsa.initFromStrings();
        String aux3 = null;

        try {
            JwtParser jwtParser = Jwts.parser().setSigningKey(secret);// No esta funcionando el signing key
        
            Jws<Claims> aux = jwtParser.parseClaimsJws(token);
            Claims aux2 = aux.getBody();
            aux3 = aux2.getSubject();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        

        System.out.println(aux3);
        return aux3;


    }

    

    public boolean validateToken(String token){
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return true;
        } catch (MalformedJwtException | UnsupportedJwtException | ExpiredJwtException | IllegalArgumentException | SignatureException e) {
           logger.error("Error comprobando el token");
            e.printStackTrace();
        }
        return false;
    }

}
