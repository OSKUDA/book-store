package np.com.oskarshrestha.bookstorebackend.service;

import np.com.oskarshrestha.bookstorebackend.entity.Book;
import np.com.oskarshrestha.bookstorebackend.model.*;
import np.com.oskarshrestha.bookstorebackend.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookRepository bookRepository;

    @Override
    public BookResponse fetchBookById(Long id) {
        Optional<Book> book = bookRepository.findById(id);
        if (book.isPresent()) {
            return BookResponse
                    .builder()
                    .book(book.get())
                    .build();
        } else {
            return BookResponse
                    .builder()
                    .book(null)
                    .build();
        }
    }

    @Override
    public BooksResponse fetchBooks(int page, int length) {
        List<MinBook> minBooks = bookRepository
                .findAll(PageRequest.of(page, length))
                .getContent()
                .stream()
                .map(MinBook::fromBook)
                .toList();
        if (minBooks.isEmpty()) {
            return BooksResponse
                    .builder()
                    .bookList(null)
                    .size(0)
                    .page(0)
                    .build();
        } else {
            return BooksResponse
                    .builder()
                    .bookList(minBooks)
                    .size(minBooks.size())
                    .page(page)
                    .build();
        }
    }

    @Override
    public AddBookResponse addBook(AddBookRequest addBookRequest) {
        if (bookRepository.existsByTitleAndAuthorAndPublicationDate(
                addBookRequest.getTitle(),
                addBookRequest.getAuthor(),
                addBookRequest.getPublicationDate()
        )) {
            return AddBookResponse.builder().status(false).build();
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
        }
        return AddBookResponse.builder().status(true).build();
    }

    @Override
    public DeleteBookResponse deleteBookById(Long id) {
        if (bookRepository.existsById(id)) {
            bookRepository.deleteById(id);
            return DeleteBookResponse
                    .builder()
                    .status(true)
                    .message("success")
                    .build();
        }else{
            return DeleteBookResponse
                    .builder()
                    .status(false)
                    .message(id+" doesn't exist")
                    .build();
        }
    }

    @Override
    public PutBookResponse updateBookById(long id, PutBookRequest putBookRequest) {
        Optional<Book> optionalBook = bookRepository.findById(id);
        if(optionalBook.isPresent()){
            Book book = optionalBook.get();
            if (putBookRequest.getPrice() >= 0) {
                book.setPrice(putBookRequest.getPrice());
            }
            if (putBookRequest.getQuantity() >= 0) {
                book.setQuantity(putBookRequest.getQuantity());
            }
            if(!putBookRequest.getTitle().isEmpty()){
                book.setTitle(putBookRequest.getTitle());
            }
            if(!putBookRequest.getAuthor().isEmpty()){
                book.setAuthor(putBookRequest.getAuthor());
            }
            if(!putBookRequest.getSummary().isEmpty()){
                book.setSummary(putBookRequest.getSummary());
            }
            if(putBookRequest.getPublicationDate() >=0){
                book.setPublicationDate(putBookRequest.getPublicationDate());
            }
            bookRepository.save(book);
            return PutBookResponse
                    .builder()
                    .status(true)
                    .message("success")
                    .build();
        }else{
            return PutBookResponse
                    .builder()
                    .status(false)
                    .message(id+" doesn't exist")
                    .build();
        }
    }
}
