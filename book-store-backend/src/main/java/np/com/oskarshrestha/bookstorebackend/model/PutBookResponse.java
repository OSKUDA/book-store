package np.com.oskarshrestha.bookstorebackend.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PutBookResponse {
    private boolean status;
    private String message;
}
