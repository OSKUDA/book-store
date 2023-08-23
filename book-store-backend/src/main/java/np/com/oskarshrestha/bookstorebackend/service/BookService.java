package np.com.oskarshrestha.bookstorebackend.service;

import np.com.oskarshrestha.bookstorebackend.request.AddBookRequest;
import np.com.oskarshrestha.bookstorebackend.util.ResponseModel;

public interface BookService {
    ResponseModel fetchBookById(Long id);

    ResponseModel fetchBooks(int page, int length);

    ResponseModel addBook(AddBookRequest addBookRequest);

}
