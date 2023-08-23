package np.com.oskarshrestha.bookstorebackend.controller;

import np.com.oskarshrestha.bookstorebackend.request.AddBookRequest;
import np.com.oskarshrestha.bookstorebackend.service.BookService;
import np.com.oskarshrestha.bookstorebackend.util.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/book")
    public ResponseEntity<ResponseModel> getBookById(
            @RequestParam("id") long id
    ) {
        ResponseModel response = bookService.fetchBookById(id);
        return new ResponseEntity<>(response, response.getHttpStatus());
    }

    @PostMapping("/book")
    public ResponseEntity<ResponseModel> postBook(
            @RequestBody AddBookRequest addBookRequest
    ) {
        ResponseModel response = bookService.addBook(addBookRequest);
        return new ResponseEntity<>(response, response.getHttpStatus());
    }


    @GetMapping("/books")
    public ResponseEntity<ResponseModel> getBooks(
            @RequestParam("page") int page,
            @RequestParam("length") int length
    ) {
        ResponseModel response = bookService.fetchBooks(page, length);
        return new ResponseEntity<>(response, response.getHttpStatus());
    }


}
