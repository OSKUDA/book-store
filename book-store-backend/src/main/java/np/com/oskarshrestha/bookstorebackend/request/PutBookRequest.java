package np.com.oskarshrestha.bookstorebackend.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PutBookRequest {
    private String title;
    private String author;
    private int publicationDate = -1;
    private String summary;
    private int price = -1;
    private int quantity = -1;
}
