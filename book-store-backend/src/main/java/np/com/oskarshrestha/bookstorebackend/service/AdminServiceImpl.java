package np.com.oskarshrestha.bookstorebackend.service;

import np.com.oskarshrestha.bookstorebackend.entity.Book;
import np.com.oskarshrestha.bookstorebackend.entity.User;
import np.com.oskarshrestha.bookstorebackend.model.*;
import np.com.oskarshrestha.bookstorebackend.repository.BookRepository;
import np.com.oskarshrestha.bookstorebackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public DeleteBookResponse deleteBookById(Long id) {
        if (bookRepository.existsById(id)) {
            bookRepository.deleteById(id);
            return DeleteBookResponse
                    .builder()
                    .status(true)
                    .message("success")
                    .build();
        } else {
            return DeleteBookResponse
                    .builder()
                    .status(false)
                    .message(id + " doesn't exist")
                    .build();
        }
    }

    @Override
    public PutBookResponse updateBookById(long id, PutBookRequest putBookRequest) {
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
            return PutBookResponse
                    .builder()
                    .status(true)
                    .message("success")
                    .build();
        } else {
            return PutBookResponse
                    .builder()
                    .status(false)
                    .message(id + " doesn't exist")
                    .build();
        }
    }

    @Override
    public MinUsersResponse fetchAllMinUser() {
        List<User> userList = userRepository.findAll();
        if (userList.isEmpty()) {
            return MinUsersResponse
                    .builder()
                    .status(false)
                    .message("users not found")
                    .minUserList(null)
                    .build();
        }
        List<MinUser> minUserList = new ArrayList<>();
        userList.forEach(
                user -> {
                    minUserList.add(MinUser.fromUser(user));
                }
        );
        return MinUsersResponse
                .builder()
                .status(true)
                .message("success")
                .minUserList(minUserList)
                .build();
    }

}
