package np.com.oskarshrestha.bookstorebackend.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import np.com.oskarshrestha.bookstorebackend.entity.Orders;
import np.com.oskarshrestha.bookstorebackend.model.MinOrder;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrdersResponse {
    private boolean status;
    private String message;
    private List<MinOrder> minOrderList;
    private int page;
    private int size;
}
