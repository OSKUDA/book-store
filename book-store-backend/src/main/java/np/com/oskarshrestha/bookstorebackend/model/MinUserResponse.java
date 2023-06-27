package np.com.oskarshrestha.bookstorebackend.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MinUserResponse {
    private boolean status;
    private String message;
    private MinUser minUser;
}
