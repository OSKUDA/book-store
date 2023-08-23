package np.com.oskarshrestha.bookstorebackend.service;

import np.com.oskarshrestha.bookstorebackend.request.AddOrderRequest;
import np.com.oskarshrestha.bookstorebackend.util.ResponseModel;

public interface OrderService {
    ResponseModel addOrder(AddOrderRequest addOrderRequest);

    ResponseModel fetchAllOrder(int page, int length);

    ResponseModel fetchOrder(long id);

    ResponseModel fetchOrderByEmail(String email);
}
