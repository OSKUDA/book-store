package np.com.oskarshrestha.bookstorebackend.service;

import lombok.extern.slf4j.Slf4j;
import np.com.oskarshrestha.bookstorebackend.entity.Book;
import np.com.oskarshrestha.bookstorebackend.entity.User;
import np.com.oskarshrestha.bookstorebackend.model.MinUser;
import np.com.oskarshrestha.bookstorebackend.repository.BookRepository;
import np.com.oskarshrestha.bookstorebackend.repository.UserRepository;
import np.com.oskarshrestha.bookstorebackend.request.PutBookRequest;
import np.com.oskarshrestha.bookstorebackend.response.DeleteBookResponse;
import np.com.oskarshrestha.bookstorebackend.response.MinUsersResponse;
import np.com.oskarshrestha.bookstorebackend.response.PutBookResponse;
import np.com.oskarshrestha.bookstorebackend.util.APIResponseStatus;
import np.com.oskarshrestha.bookstorebackend.util.ResponseModel;
import np.com.oskarshrestha.bookstorebackend.util.ResponseStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class AdminServiceImpl implements AdminService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private APIResponseStatus apiResponseStatus;

    @Override
    public ResponseModel deleteBookById(Long id) {
        try {
            if (bookRepository.existsById(id)) {
                Optional<Book> book = bookRepository.findById(id);
                if (book.isPresent()) {
                    book.get().setDeleted(true);
                    bookRepository.save(book.get());
                    return ResponseStatus.success(
                            apiResponseStatus.SUCCESS_MESSAGE,
                            DeleteBookResponse
                                    .builder()
                                    .status(true)
                                    .message("success")
                                    .build(),
                            HttpStatus.OK,
                            true
                    );
                }
            } else {
                return ResponseStatus.success(
                        apiResponseStatus.SUCCESS_MESSAGE,
                        DeleteBookResponse
                                .builder()
                                .status(false)
                                .message(id + " doesn't exist")
                                .build(),
                        HttpStatus.OK,
                        true
                );
            }
        } catch (Exception e) {
            log.error("Exception: deleteBookById: " + Arrays.asList(e.getStackTrace()));
            return ResponseStatus.error(
                    apiResponseStatus.FAILURE_MESSAGE,
                    e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    false
            );
        }
        return ResponseStatus.error(
                apiResponseStatus.FAILURE_MESSAGE,
                "response model is null",
                HttpStatus.INTERNAL_SERVER_ERROR,
                false
        );
    }

    @Override
    public ResponseModel updateBookById(long id, PutBookRequest putBookRequest) {
        ResponseModel rs;
        try {
            Optional<Book> optionalBook = bookRepository.findById(id);
            if (optionalBook.isPresent()) {
                Book book = optionalBook.get();
                if (putBookRequest.getPrice() >= 0) {
                    book.setPrice(putBookRequest.getPrice());
                }
                if (putBookRequest.getQuantity() >= 0) {
                    book.setQuantity(putBookRequest.getQuantity());
                }
                if (putBookRequest.getTitle() != null && !putBookRequest.getTitle().isEmpty()) {
                    book.setTitle(putBookRequest.getTitle());
                }
                if (putBookRequest.getAuthor() != null && !putBookRequest.getAuthor().isEmpty()) {
                    book.setAuthor(putBookRequest.getAuthor());
                }
                if (putBookRequest.getSummary() != null && !putBookRequest.getSummary().isEmpty()) {
                    book.setSummary(putBookRequest.getSummary());
                }
                if (putBookRequest.getPublicationDate() >= 0) {
                    book.setPublicationDate(putBookRequest.getPublicationDate());
                }
                bookRepository.save(book);
                rs = ResponseStatus.success(
                        apiResponseStatus.SUCCESS_MESSAGE,
                        PutBookResponse
                                .builder()
                                .status(true)
                                .message("success")
                                .build(),
                        HttpStatus.OK,
                        true
                );
            } else {
                rs = ResponseStatus.success(
                        apiResponseStatus.SUCCESS_MESSAGE,
                        PutBookResponse
                                .builder()
                                .status(false)
                                .message(id + " doesn't exist")
                                .build(),
                        HttpStatus.OK,
                        true
                );
            }
        } catch (Exception e) {
            log.error("Exception: updateBookById: " + Arrays.asList(e.getStackTrace()));
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
    public ResponseModel fetchAllMinUser() {
        ResponseModel rs;
        try {
            List<User> userList = userRepository.findAll();
            if (userList.size() > 0) {
                List<MinUser> minUserList = new ArrayList<>();
                userList.forEach(
                        user -> {
                            minUserList.add(MinUser.fromUser(user));
                        }
                );
                rs = ResponseStatus.success(
                        apiResponseStatus.DATA_FOUND_MESSAGE,
                        MinUsersResponse
                                .builder()
                                .status(true)
                                .message("success")
                                .minUserList(minUserList)
                                .build(),
                        HttpStatus.OK,
                        true
                );
            } else {
                rs = ResponseStatus.success(
                        apiResponseStatus.DATA_NOT_FOUND_MESSAGE,
                        MinUsersResponse
                                .builder()
                                .status(false)
                                .message("users not found")
                                .minUserList(null)
                                .build(),
                        HttpStatus.OK,
                        true
                );
            }

        } catch (Exception e) {
            log.error("Exception: fetchAllMinUser: " + Arrays.asList(e.getStackTrace()));
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
