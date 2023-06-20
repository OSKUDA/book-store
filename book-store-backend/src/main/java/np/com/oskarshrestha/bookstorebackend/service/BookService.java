package np.com.oskarshrestha.bookstorebackend.service;

import np.com.oskarshrestha.bookstorebackend.model.*;

public interface BookService {
    BookResponse fetchBookById(Long id);

    BooksResponse fetchBooks(int page, int length);

    AddBookResponse addBook(AddBookRequest addBookRequest);

    DeleteBookResponse deleteBookById(Long id);

    PutBookResponse updateBookById(long id, PutBookRequest putBookRequest);
}
