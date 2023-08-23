package np.com.oskarshrestha.bookstorebackend.controller;

import np.com.oskarshrestha.bookstorebackend.request.AddBookRequest;
import np.com.oskarshrestha.bookstorebackend.response.DeleteBookResponse;
import np.com.oskarshrestha.bookstorebackend.response.MinUsersResponse;
import np.com.oskarshrestha.bookstorebackend.request.PutBookRequest;
import np.com.oskarshrestha.bookstorebackend.response.PutBookResponse;
import np.com.oskarshrestha.bookstorebackend.service.AdminService;
import np.com.oskarshrestha.bookstorebackend.service.BookService;
import np.com.oskarshrestha.bookstorebackend.service.OrderService;
import np.com.oskarshrestha.bookstorebackend.util.ResponseModel;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private BookService bookService;

    @Autowired
    private OrderService orderService;

    @DeleteMapping("/book")
    public ResponseEntity<ResponseModel> deleteBook(
            @RequestParam("id") long id
    ) {
        ResponseModel response = adminService.deleteBookById(id);
        return new ResponseEntity<>(response, response.getHttpStatus());
    }

    @PostMapping("/book")
    public ResponseEntity<ResponseModel> postBook(
            @RequestBody AddBookRequest addBookRequest
    ) {
        ResponseModel response = bookService.addBook(addBookRequest);
        return new ResponseEntity<>(response, response.getHttpStatus());
    }

    @PutMapping("/book")
    public ResponseEntity<ResponseModel> updateBook(
            @RequestParam("id") long id,
            @RequestBody PutBookRequest putBookRequest
    ) {
        ResponseModel response = adminService.updateBookById(id, putBookRequest);
        return new ResponseEntity<>(response, response.getHttpStatus());
    }

    @GetMapping("/orders")
    public ResponseEntity<ResponseModel> getOrders(
            @RequestParam("page") int page,
            @RequestParam("length") int length
    ) {
        ResponseModel response = orderService.fetchAllOrder(page, length);
        return new ResponseEntity<>(response, response.getHttpStatus());
    }

    @GetMapping("/users")
    public ResponseEntity<ResponseModel> getAllMinUserDetails() {
        ResponseModel response = adminService.fetchAllMinUser();
        return new ResponseEntity<>(response, response.getHttpStatus());
    }

}
