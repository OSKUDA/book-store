package np.com.oskarshrestha.bookstorebackend.controller;

import np.com.oskarshrestha.bookstorebackend.model.*;
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
    ){
        BookResponse response = bookService.fetchBookById(id);
        if(response.getBook() != null){
            return ResponseEntity.ok(response);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/book")
    public ResponseEntity<AddBookResponse> postBook(
            @RequestBody AddBookRequest addBookRequest
    ){
        AddBookResponse response = bookService.addBook(addBookRequest);
        if(response.isStatus()){
            return ResponseEntity.ok(response);
        }else{
            return ResponseEntity.status(409).body(response);
        }
    }

    @DeleteMapping("/book")
    public ResponseEntity<DeleteBookResponse> deleteBook(
            @RequestParam("id") long id
    ){
        DeleteBookResponse response = bookService.deleteBookById(id);
        if(response.isStatus()){
            return ResponseEntity.ok(response);
        }else{
            return ResponseEntity.status(404).body(response);
        }
    }

    @PutMapping("/book")
    public ResponseEntity<PutBookResponse> updateBook(
            @RequestParam("id") long id,
            @RequestBody PutBookRequest putBookRequest
    ){
        System.out.println("here");
        PutBookResponse response = bookService.updateBookById(id, putBookRequest);
        if(response.isStatus()){
            return ResponseEntity.ok(response);
        }else{
            return ResponseEntity.status(404).body(response);
        }
    }

    @GetMapping("/books")
    public ResponseEntity<BooksResponse> getBooks(
            @RequestParam("page") int page,
            @RequestParam("length") int length
    ){
        BooksResponse response = bookService.fetchBooks(page, length);
        if(response.getBookList() != null){
            return ResponseEntity.ok(response);
        }else {
            return ResponseEntity.notFound().build();
        }
    }


}
