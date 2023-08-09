package np.com.oskarshrestha.bookstorebackend.service;

import np.com.oskarshrestha.bookstorebackend.request.AddOrderRequest;
import np.com.oskarshrestha.bookstorebackend.response.AddOrderResponse;
import np.com.oskarshrestha.bookstorebackend.response.OrderByEmailResponse;
import np.com.oskarshrestha.bookstorebackend.response.OrderResponse;
import np.com.oskarshrestha.bookstorebackend.response.OrdersResponse;

public interface OrderService {
    AddOrderResponse addOrder(AddOrderRequest addOrderRequest);

    OrdersResponse fetchAllOrder(int page, int length);

    OrderResponse fetchOrder(long id);

    OrderByEmailResponse fetchOrderByEmail(String email);
}
