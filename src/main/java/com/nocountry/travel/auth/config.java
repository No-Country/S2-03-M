package com.nocountry.travel.auth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class config{

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http)throws Exception{
        http.authorizeHttpRequests((authz) -> {
            try {
                authz
                    .anyRequest().authenticated()
                    .and().oauth2Login().and().logout().logoutUrl("/logout");
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
            return http.build();
    }

    
    

}
