package np.com.oskarshrestha.bookstorebackend.controller;

import np.com.oskarshrestha.bookstorebackend.model.UserRegisterRequest;
import np.com.oskarshrestha.bookstorebackend.model.UserRegisterResponse;
import np.com.oskarshrestha.bookstorebackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
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
}
