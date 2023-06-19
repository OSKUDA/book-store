package np.com.oskarshrestha.bookstorebackend.service;

import np.com.oskarshrestha.bookstorebackend.entity.Book;
import np.com.oskarshrestha.bookstorebackend.model.BookResponse;
import np.com.oskarshrestha.bookstorebackend.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
