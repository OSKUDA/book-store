package np.com.oskarshrestha.bookstorebackend.controller;

import np.com.oskarshrestha.bookstorebackend.request.UserAuthenticationRequest;
import np.com.oskarshrestha.bookstorebackend.request.UserRegisterRequest;
import np.com.oskarshrestha.bookstorebackend.service.UserService;
import np.com.oskarshrestha.bookstorebackend.util.ResponseModel;
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
    public ResponseEntity<ResponseModel> registerUser(
            @RequestBody UserRegisterRequest userRegisterRequest
    ) {
        ResponseModel response = userService.registerUser(userRegisterRequest);
        return new ResponseEntity<>(response, response.getHttpStatus());
    }

    @PostMapping("/authenticate")
    public ResponseEntity<ResponseModel> authenticate(
            @RequestBody UserAuthenticationRequest userAuthenticationRequest
    ) {
        ResponseModel response = userService.authenticate(userAuthenticationRequest);
        return new ResponseEntity<>(response, response.getHttpStatus());
    }


}
