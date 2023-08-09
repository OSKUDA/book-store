package np.com.oskarshrestha.bookstorebackend.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import np.com.oskarshrestha.bookstorebackend.model.MinBook;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BooksResponse {
    private List<MinBook> bookList;

    private int size;

    private int page;
}
