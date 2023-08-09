package np.com.oskarshrestha.bookstorebackend.controller;

import np.com.oskarshrestha.bookstorebackend.response.MinUserResponse;
import np.com.oskarshrestha.bookstorebackend.service.UserService;
import np.com.oskarshrestha.bookstorebackend.util.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping("/user")
    public ResponseEntity<ResponseModel> getMinUserDetails(
            @RequestParam("email") String email
    ){
        ResponseModel response = userService.fetchMinUser(email);
        return new ResponseEntity<>(response, response.getHttpStatus());
    }


}
