package np.com.oskarshrestha.bookstorebackend.service;

import np.com.oskarshrestha.bookstorebackend.model.*;

public interface OrderService {
    AddOrderResponse addOrder(AddOrderRequest addOrderRequest);

    OrdersResponse fetchAllOrder(int page, int length);

    OrderResponse fetchOrder(long id);

    OrderByEmailResponse fetchOrderByEmail(String email);
}
