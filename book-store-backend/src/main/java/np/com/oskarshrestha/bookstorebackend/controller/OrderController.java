package np.com.oskarshrestha.bookstorebackend.controller;

import np.com.oskarshrestha.bookstorebackend.request.AddOrderRequest;
import np.com.oskarshrestha.bookstorebackend.response.AddOrderResponse;
import np.com.oskarshrestha.bookstorebackend.response.OrderByEmailResponse;
import np.com.oskarshrestha.bookstorebackend.response.OrderResponse;
import np.com.oskarshrestha.bookstorebackend.response.OrdersResponse;
import np.com.oskarshrestha.bookstorebackend.service.OrderService;
import np.com.oskarshrestha.bookstorebackend.util.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/order")
    public ResponseEntity<ResponseModel> addOrder(
            @RequestBody AddOrderRequest addOrderRequest
    ) {
        ResponseModel response = orderService.addOrder(addOrderRequest);
        return new ResponseEntity<>(response, response.getHttpStatus());
    }

    @GetMapping("/order/{id}")
    public ResponseEntity<ResponseModel> fetchOrderById(
            @PathVariable("id") long id
    ) {
        ResponseModel response = orderService.fetchOrder(id);
        return new ResponseEntity<>(response, response.getHttpStatus());
    }

    @GetMapping("/order")
    public ResponseEntity<ResponseModel> fetchOrderByEmail(
            @RequestParam("email") String email
    ) {
        ResponseModel response = orderService.fetchOrderByEmail(email);
        return new ResponseEntity<>(response, response.getHttpStatus());
    }


}
