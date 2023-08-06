package np.com.oskarshrestha.bookstorebackend.controller;

import np.com.oskarshrestha.bookstorebackend.model.DeleteBookResponse;
import np.com.oskarshrestha.bookstorebackend.model.MinUsersResponse;
import np.com.oskarshrestha.bookstorebackend.model.PutBookRequest;
import np.com.oskarshrestha.bookstorebackend.model.PutBookResponse;
import np.com.oskarshrestha.bookstorebackend.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @DeleteMapping("/book")
    public ResponseEntity<DeleteBookResponse> deleteBook(
            @RequestParam("id") long id
    ) {
        DeleteBookResponse response = adminService.deleteBookById(id);
        if (response.isStatus()) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(404).body(response);
        }
    }

    @PutMapping("/book")
    public ResponseEntity<PutBookResponse> updateBook(
            @RequestParam("id") long id,
            @RequestBody PutBookRequest putBookRequest
    ) {
        System.out.println("here");
        PutBookResponse response = adminService.updateBookById(id, putBookRequest);
        if (response.isStatus()) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(404).body(response);
        }
    }

    @GetMapping("/users")
    public ResponseEntity<MinUsersResponse> getAllMinUserDetails() {
        MinUsersResponse response = adminService.fetchAllMinUser();
        if (response.isStatus()) {
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.status(400).body(response);
    }

}
