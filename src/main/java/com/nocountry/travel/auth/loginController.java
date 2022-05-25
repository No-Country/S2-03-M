package com.nocountry.travel.auth;

import java.security.Principal;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// -------------------CONTROLLER DE PRUEBA----------------------------

@RestController
public class loginController {
    
    @GetMapping
    public String welcome(){
        return "Welcome to Google!!";
    }
    
    @GetMapping("/user")
    public Principal user(Principal principal){
        System.out.println("username: " + principal.getName());
        return principal;
    }

}
