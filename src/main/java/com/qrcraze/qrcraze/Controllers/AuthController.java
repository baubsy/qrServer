package com.qrcraze.qrcraze.Controllers;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.qrcraze.qrcraze.Data.LoginResponse;
import com.qrcraze.qrcraze.Data.UserRepository;
import com.qrcraze.qrcraze.Models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.Optional;

@Controller
public class AuthController {
    @Autowired
    private UserRepository userRepository;

    private static final String CLIENTID = System.getenv("CLIENTID");
    GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(new NetHttpTransport(), new GsonFactory()).
            setAudience(Collections.singletonList(CLIENTID))
            .build();

    @CrossOrigin(origins = "*")
    @ResponseBody
    @PostMapping("/register")
    public LoginResponse register(@RequestParam String idtoken) throws GeneralSecurityException, IOException {
        GoogleIdToken gToken = verifier.verify(idtoken);

        if(gToken != null){
            //TODO check if registered already, if so just sign in
            Payload payload = gToken.getPayload();
            String email = (String) payload.getEmail();
            String sub = payload.getSubject();
            userRepository.save(new User(email, email, sub));
            return new LoginResponse("registered");
        } else{
            return new LoginResponse("bad token");
        }
    }
    @CrossOrigin(origins = "*")
    @ResponseBody
    @PostMapping("/signin")
    public LoginResponse signIn(@RequestParam String idtoken) throws GeneralSecurityException, IOException {
        GoogleIdToken gToken = verifier.verify(idtoken);

        if(gToken != null){
            //check if user exists
            Payload payload = gToken.getPayload();
            String sub = payload.getSubject();

            Optional<User> currentUser = userRepository.findBygoogleID(sub);
            if(currentUser.isPresent()){
                //sign logic here

                return new LoginResponse("signed in");
            } else{
                return new LoginResponse("no user found");
            }
        } else{
            return new LoginResponse("bad token");
        }


        //if not let front end know to go to /register
    }
}
