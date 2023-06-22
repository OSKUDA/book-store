package np.com.oskarshrestha.bookstorebackend.controller;

import np.com.oskarshrestha.bookstorebackend.entity.Orders;
import np.com.oskarshrestha.bookstorebackend.model.AddOrderRequest;
import np.com.oskarshrestha.bookstorebackend.model.AddOrderResponse;
import np.com.oskarshrestha.bookstorebackend.model.OrdersResponse;
import np.com.oskarshrestha.bookstorebackend.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/order")
    public ResponseEntity<AddOrderResponse> addOrder(
            @RequestBody AddOrderRequest addOrderRequest
    ){
        AddOrderResponse response = orderService.addOrder(addOrderRequest);
        if(response.isStatus()){
            return ResponseEntity.ok(response);
        }else{
            return ResponseEntity.status(400).body(response);
        }
    }

    @GetMapping("/orders")
    public ResponseEntity<OrdersResponse> getOrders(
            @RequestParam("page") int page,
            @RequestParam("length") int length
    ){
        OrdersResponse response = orderService.fetchAllOrder(page, length);
        if(response.isStatus()){
            return ResponseEntity.ok(response);
        }else{
            return ResponseEntity.notFound().build();
        }
    }
}
