package np.com.oskarshrestha.bookstorebackend.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import np.com.oskarshrestha.bookstorebackend.model.MinUser;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MinUserResponse {
    private boolean status;
    private String message;
    private MinUser minUser;
}
