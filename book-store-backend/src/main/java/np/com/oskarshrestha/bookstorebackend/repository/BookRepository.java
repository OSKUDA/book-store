package np.com.oskarshrestha.bookstorebackend.repository;

import np.com.oskarshrestha.bookstorebackend.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    boolean existsByTitleAndAuthorAndPublicationDate(String title, String author, int publicationDate);
}
