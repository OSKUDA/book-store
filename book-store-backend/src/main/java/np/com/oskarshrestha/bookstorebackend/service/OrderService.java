package np.com.oskarshrestha.bookstorebackend.service;

import np.com.oskarshrestha.bookstorebackend.model.AddOrderRequest;
import np.com.oskarshrestha.bookstorebackend.model.AddOrderResponse;
import np.com.oskarshrestha.bookstorebackend.model.OrderResponse;
import np.com.oskarshrestha.bookstorebackend.model.OrdersResponse;

public interface OrderService {
    AddOrderResponse addOrder(AddOrderRequest addOrderRequest);

    OrdersResponse fetchAllOrder(int page, int length);

    OrderResponse fetchOrder(long id);
}
