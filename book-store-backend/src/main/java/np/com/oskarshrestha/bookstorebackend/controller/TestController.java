package np.com.oskarshrestha.bookstorebackend.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@CrossOrigin(origins = "http://localhost:5173/")
public class TestController {

    @GetMapping("/api/v1/hello")
    public String hello(){
        return "Hello";
    }

    @GetMapping("/api/v1/secure")
    public String secure(){
        return "This is secure data";
    }
}
