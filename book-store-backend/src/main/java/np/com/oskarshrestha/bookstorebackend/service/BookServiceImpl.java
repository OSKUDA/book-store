package np.com.oskarshrestha.bookstorebackend.service;

import np.com.oskarshrestha.bookstorebackend.entity.Book;
import np.com.oskarshrestha.bookstorebackend.model.BookResponse;
import np.com.oskarshrestha.bookstorebackend.model.BooksResponse;
import np.com.oskarshrestha.bookstorebackend.model.MinBook;
import np.com.oskarshrestha.bookstorebackend.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService{
    @Autowired
    private BookRepository bookRepository;

    @Override
    public BookResponse fetchBookById(Long id){
        Optional<Book> book = bookRepository.findById(id);
        if(book.isPresent()){
            return BookResponse
                    .builder()
                    .book(book.get())
                    .build();
        }else{
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
        if(minBooks.isEmpty()){
            return BooksResponse
                    .builder()
                    .bookList(null)
                    .size(0)
                    .page(0)
                    .build();
        }else{
            return BooksResponse
                    .builder()
                    .bookList(minBooks)
                    .size(minBooks.size())
                    .page(page)
                    .build();
        }
    }
}
