package com.nocountry.travel.auth;

import java.io.IOException;
import java.util.Collections;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
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

    @PostMapping("/google")
    public ResponseEntity<?> google(@RequestBody TokenDto tokenDto) throws IOException{ //envia token del front para que google lo valide

        final NetHttpTransport transport = new NetHttpTransport();
        final GsonFactory gsonFactory = GsonFactory.getDefaultInstance();
        GoogleIdTokenVerifier.Builder verifier = 
        new GoogleIdTokenVerifier.Builder(transport, gsonFactory)
        .setAudience(Collections.singletonList(googleClientId));

        final GoogleIdToken  googleIdToken= GoogleIdToken.parse(verifier.getJsonFactory(), tokenDto.getValue());
        final GoogleIdToken.Payload payload = googleIdToken.getPayload();

        return ResponseEntity.ok().body(payload);
    }
}
