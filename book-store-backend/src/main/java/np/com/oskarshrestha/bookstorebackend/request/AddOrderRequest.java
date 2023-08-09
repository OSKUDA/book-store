package np.com.oskarshrestha.bookstorebackend.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddOrderRequest {
    private String email;
    private List<Long> bookIdList;
    private String deliveryAddress;
}
