package com.qrcraze.qrcraze.Controllers;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.qrcraze.qrcraze.Data.UserRepository;
import com.qrcraze.qrcraze.Models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;

@Controller
public class AuthController {
    @Autowired
    private UserRepository userRepository;

    private static final String CLIENTID = System.getenv("CLIENTID");
    GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(new NetHttpTransport(), new GsonFactory()).
            setAudience(Collections.singletonList(CLIENTID))
            .build();

    @CrossOrigin(origins = "*")
    @PostMapping("/SignIn")
    public void register(@RequestParam String idtoken) throws GeneralSecurityException, IOException {
        GoogleIdToken gToken = verifier.verify(idtoken);

        if(gToken != null){
            Payload payload = gToken.getPayload();
            String name = (String) payload.get("name");

            //testing token verification
            userRepository.save(new User("pathogenic@gmail.com", name));
        }
    }
}
