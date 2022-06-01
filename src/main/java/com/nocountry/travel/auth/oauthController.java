package com.nocountry.travel.auth;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.nocountry.travel.entity.Rol;
import com.nocountry.travel.entity.Usuario;
import com.nocountry.travel.enums.RolNombre;
import com.nocountry.travel.security.UserDetailsServiceImpl;
import com.nocountry.travel.security.jwt.JwtProvider;
import com.nocountry.travel.service.RolService;
import com.nocountry.travel.service.UsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/oauth")
@CrossOrigin
public class oauthController {
    
    @Value("${google.clientId}")
    String googleClientId;

    @Value("${secretPsw}")
    String secretPsw;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserDetailsServiceImpl userDetailsServiceImpl;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtProvider jwtProvider;

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    RolService rolService;

    @PostMapping("/google")
    public ResponseEntity<?> google(@RequestBody TokenDto tokenDto) throws IOException{ //envia token del front para que google lo valide

        final NetHttpTransport transport = new NetHttpTransport();
        final GsonFactory gsonFactory = GsonFactory.getDefaultInstance();
        GoogleIdTokenVerifier.Builder verifier = 
        new GoogleIdTokenVerifier.Builder(transport, gsonFactory)
        .setAudience(Collections.singletonList(googleClientId));

        final GoogleIdToken  googleIdToken= GoogleIdToken.parse(verifier.getJsonFactory(), tokenDto.getValue());
        final GoogleIdToken.Payload payload = googleIdToken.getPayload();

        Usuario usuario = new Usuario();
        if (usuarioService.existsEmail(payload.getEmail())) {
            usuario = usuarioService.getByEmail(payload.getEmail()).get();
        }else{
            usuario = saveUsuario(payload.getEmail());
        }
        
        TokenDto tokenRes = login(usuario);
        return ResponseEntity.ok(tokenRes);
    }

    private TokenDto login(Usuario usuario){

        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(usuario.getEmail(), secretPsw)
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);;
        String jwt = jwtProvider.generateToken(authentication);
        TokenDto tokenDto = new TokenDto();
        tokenDto.setValue(jwt);;
        return tokenDto;


    }

    private Usuario saveUsuario(String email){
        Usuario usuario = new Usuario(email, passwordEncoder.encode(secretPsw));
        Rol rolUser = rolService.getByRolNombre(RolNombre.ROLE_USER).get();
        Set<Rol> roles = new HashSet<>();
        roles.add(rolUser);
        usuario.setRoles(roles);
        return usuarioService.save(usuario);
    }
}
