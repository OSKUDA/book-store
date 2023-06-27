package np.com.oskarshrestha.bookstorebackend.controller;

import np.com.oskarshrestha.bookstorebackend.model.MinUserResponse;
import np.com.oskarshrestha.bookstorebackend.model.MinUsersResponse;
import np.com.oskarshrestha.bookstorebackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api/v1")
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping("/user")
    public ResponseEntity<MinUserResponse> getMinUserDetails(
            @RequestParam("email") String email
    ){
        MinUserResponse response = userService.fetchMinUser(email);
        if(response.isStatus()){
            return ResponseEntity.ok(response);
        }else{
            return ResponseEntity.badRequest().body(response);
        }
    }

    @GetMapping("/users")
    public ResponseEntity<MinUsersResponse> getAllMinUserDetails(){
        MinUsersResponse response = userService.fetchAllMinUser();
        if(response.isStatus()){
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.status(400).body(response);
    }
}
