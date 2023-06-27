package np.com.oskarshrestha.bookstorebackend.controller;

import np.com.oskarshrestha.bookstorebackend.model.*;
import np.com.oskarshrestha.bookstorebackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api/v1")
public class AuthenticationController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserRegisterResponse> registerUser(
            @RequestBody UserRegisterRequest userRegisterRequest
    ){
        UserRegisterResponse response = userService.registerUser(userRegisterRequest);
        if(response.isRegistrationSuccess()){
            return ResponseEntity.ok(response);
        }else{
            return ResponseEntity.status(409).body(response);
        }
    }

    @PostMapping("/authenticate")
    public ResponseEntity<UserAuthenticationResponse> authenticate(
            @RequestBody UserAuthenticationRequest userAuthenticationRequest
    ){
        UserAuthenticationResponse response = userService.authenticate(userAuthenticationRequest);
        if(response.getToken() == null){
            return ResponseEntity.status(401).body(response);
        }else{
            return ResponseEntity.ok(response);
        }
    }


}
