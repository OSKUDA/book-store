package np.com.oskarshrestha.bookstorebackend.service;

import np.com.oskarshrestha.bookstorebackend.model.AddBookRequest;
import np.com.oskarshrestha.bookstorebackend.model.AddBookResponse;
import np.com.oskarshrestha.bookstorebackend.model.BookResponse;
import np.com.oskarshrestha.bookstorebackend.model.BooksResponse;

public interface BookService {
    BookResponse fetchBookById(Long id);

    BooksResponse fetchBooks(int page, int length);

    AddBookResponse addBook(AddBookRequest addBookRequest);
}
