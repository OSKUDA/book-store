package np.com.oskarshrestha.bookstorebackend.controller;

import np.com.oskarshrestha.bookstorebackend.request.AddBookRequest;
import np.com.oskarshrestha.bookstorebackend.response.AddBookResponse;
import np.com.oskarshrestha.bookstorebackend.response.BookResponse;
import np.com.oskarshrestha.bookstorebackend.response.BooksResponse;
import np.com.oskarshrestha.bookstorebackend.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
//@CrossOrigin(origins = "http://localhost:5173/")
@RequestMapping("/api/v1")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/book")
    public ResponseEntity<BookResponse> getBookById(
            @RequestParam("id") long id
    ) {
        BookResponse response = bookService.fetchBookById(id);
        if (response.getBook() != null) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/book")
    public ResponseEntity<AddBookResponse> postBook(
            @RequestBody AddBookRequest addBookRequest
    ) {
        AddBookResponse response = bookService.addBook(addBookRequest);
        if (response.isStatus()) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(409).body(response);
        }
    }



    @GetMapping("/books")
    public ResponseEntity<BooksResponse> getBooks(
            @RequestParam("page") int page,
            @RequestParam("length") int length
    ) {
        BooksResponse response = bookService.fetchBooks(page, length);
        if (response.getBookList() != null) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}
