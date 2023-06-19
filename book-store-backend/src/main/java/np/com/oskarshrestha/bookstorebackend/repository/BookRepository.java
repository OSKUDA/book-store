package np.com.oskarshrestha.bookstorebackend.repository;

import np.com.oskarshrestha.bookstorebackend.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {

}
