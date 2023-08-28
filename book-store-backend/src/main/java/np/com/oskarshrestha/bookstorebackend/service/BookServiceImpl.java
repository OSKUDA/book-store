package np.com.oskarshrestha.bookstorebackend.service;

import lombok.extern.slf4j.Slf4j;
import np.com.oskarshrestha.bookstorebackend.entity.Book;
import np.com.oskarshrestha.bookstorebackend.model.MinBook;
import np.com.oskarshrestha.bookstorebackend.repository.BookRepository;
import np.com.oskarshrestha.bookstorebackend.request.AddBookRequest;
import np.com.oskarshrestha.bookstorebackend.response.AddBookResponse;
import np.com.oskarshrestha.bookstorebackend.response.BookResponse;
import np.com.oskarshrestha.bookstorebackend.response.BooksResponse;
import np.com.oskarshrestha.bookstorebackend.util.APIResponseStatus;
import np.com.oskarshrestha.bookstorebackend.util.ResponseModel;
import np.com.oskarshrestha.bookstorebackend.util.ResponseStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class BookServiceImpl implements BookService {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private APIResponseStatus apiResponseStatus;

    @Override
    public ResponseModel fetchBookById(Long id) {
        ResponseModel rs;
        try {
            Optional<Book> book = bookRepository.findById(id);
            rs = book.map(value -> ResponseStatus.success(
                    apiResponseStatus.DATA_FOUND_MESSAGE,
                    BookResponse
                            .builder()
                            .book(value)
                            .build(),
                    HttpStatus.OK,
                    true
            )).orElseGet(() -> ResponseStatus.success(
                    apiResponseStatus.DATA_NOT_FOUND_MESSAGE,
                    BookResponse
                            .builder()
                            .book(null)
                            .build(),
                    HttpStatus.OK,
                    true
            ));
        } catch (Exception e) {
            log.error("Exception: fetchBookById: " + Arrays.asList(e.getStackTrace()));
            rs = ResponseStatus.error(
                    apiResponseStatus.FAILURE_MESSAGE,
                    e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    false
            );
        }
        return rs;
    }

    @Override
    public ResponseModel fetchBooks(int page, int length) {
        ResponseModel rs;
        try {
            List<MinBook> minBooks = bookRepository
                    .findByIsDeletedFalse(PageRequest.of(page, length, Sort.by(Sort.Direction.DESC, "id")))
                    .getContent()
                    .stream()
                    .map(MinBook::fromBook)
                    .toList();
            if (minBooks.size() > 0) {
                rs = ResponseStatus.success(
                        apiResponseStatus.DATA_FOUND_MESSAGE,
                        BooksResponse
                                .builder()
                                .bookList(minBooks)
                                .size(minBooks.size())
                                .page(page)
                                .build(),
                        HttpStatus.OK,
                        true
                );
            } else {
                rs = ResponseStatus.success(
                        apiResponseStatus.DATA_NOT_FOUND_MESSAGE,
                        BooksResponse
                                .builder()
                                .bookList(minBooks)
                                .size(0)
                                .page(page)
                                .build(),
                        HttpStatus.OK,
                        true
                );
            }
        } catch (Exception e) {
            log.error("Exception: fetchBooks : " + Arrays.asList(e.getStackTrace()));
            rs = ResponseStatus.error(
                    apiResponseStatus.FAILURE_MESSAGE,
                    e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    false
            );
        }
        return rs;
    }

    @Override
    public ResponseModel addBook(AddBookRequest addBookRequest) {
        ResponseModel rs;
        try {
            if (bookRepository.existsByTitleAndAuthorAndPublicationDate(
                    addBookRequest.getTitle(),
                    addBookRequest.getAuthor(),
                    addBookRequest.getPublicationDate()
            )) {
                rs = ResponseStatus.success(
                        apiResponseStatus.SUCCESS_MESSAGE,
                        AddBookResponse.builder().status(false).build(),
                        HttpStatus.OK,
                        true
                );
            } else {
                bookRepository.save(
                        Book
                                .builder()
                                .title(addBookRequest.getTitle())
                                .author(addBookRequest.getAuthor())
                                .publicationDate(addBookRequest.getPublicationDate())
                                .summary(addBookRequest.getSummary())
                                .quantity(addBookRequest.getQuantity())
                                .price(addBookRequest.getPrice())
                                .build()
                );
                rs = ResponseStatus.success(
                        apiResponseStatus.SUCCESS_MESSAGE,
                        AddBookResponse.builder().status(true).build(),
                        HttpStatus.OK,
                        true
                );
            }
        } catch (Exception e) {
            log.error("Exception: addBook : " + Arrays.asList(e.getStackTrace()));
            rs = ResponseStatus.error(
                    apiResponseStatus.FAILURE_MESSAGE,
                    e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    false
            );
        }
        return rs;
    }


}
