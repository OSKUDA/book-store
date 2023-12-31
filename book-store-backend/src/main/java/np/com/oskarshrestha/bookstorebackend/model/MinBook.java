package np.com.oskarshrestha.bookstorebackend.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import np.com.oskarshrestha.bookstorebackend.entity.Book;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MinBook {
    private long id;

    private String title;

    private String author;

    private int publicationDate;

    private int quantity;

    private int price;

    public static MinBook fromBook(Book book) {
        return new MinBook(book.getId(), book.getTitle(), book.getAuthor(), book.getPublicationDate(), book.getQuantity(), book.getPrice());
    }
}
