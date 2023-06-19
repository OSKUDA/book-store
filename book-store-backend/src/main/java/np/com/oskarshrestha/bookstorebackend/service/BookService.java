package np.com.oskarshrestha.bookstorebackend.service;

import np.com.oskarshrestha.bookstorebackend.model.BookResponse;
import np.com.oskarshrestha.bookstorebackend.model.BooksResponse;
import org.springframework.http.ResponseEntity;

public interface BookService {
    BookResponse fetchBookById(Long id);

    BooksResponse fetchBooks(int page, int length);
}
