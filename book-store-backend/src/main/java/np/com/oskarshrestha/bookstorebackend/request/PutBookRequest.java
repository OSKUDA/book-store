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

    @Builder.Default
    private int publicationDate = -1;

    private String summary;

    @Builder.Default
    private int price = -1;

    @Builder.Default
    private int quantity = -1;

}
