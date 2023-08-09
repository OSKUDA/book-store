package np.com.oskarshrestha.bookstorebackend.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import np.com.oskarshrestha.bookstorebackend.entity.Book;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddBookRequest {
    private String title;
    private String author;
    private int publicationDate;
    private String summary;
    private int quantity;
    private int price;
}
