package np.com.oskarshrestha.bookstorebackend.service;

import np.com.oskarshrestha.bookstorebackend.request.AddBookRequest;
import np.com.oskarshrestha.bookstorebackend.response.AddBookResponse;
import np.com.oskarshrestha.bookstorebackend.response.BookResponse;
import np.com.oskarshrestha.bookstorebackend.response.BooksResponse;

public interface BookService {
    BookResponse fetchBookById(Long id);

    BooksResponse fetchBooks(int page, int length);

    AddBookResponse addBook(AddBookRequest addBookRequest);

}
