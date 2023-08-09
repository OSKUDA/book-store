package np.com.oskarshrestha.bookstorebackend.service;

import np.com.oskarshrestha.bookstorebackend.entity.Book;
import np.com.oskarshrestha.bookstorebackend.entity.Orders;
import np.com.oskarshrestha.bookstorebackend.entity.User;
import np.com.oskarshrestha.bookstorebackend.model.*;
import np.com.oskarshrestha.bookstorebackend.repository.BookRepository;
import np.com.oskarshrestha.bookstorebackend.repository.OrderRepository;
import np.com.oskarshrestha.bookstorebackend.repository.UserRepository;
import np.com.oskarshrestha.bookstorebackend.request.AddOrderRequest;
import np.com.oskarshrestha.bookstorebackend.response.AddOrderResponse;
import np.com.oskarshrestha.bookstorebackend.response.OrderByEmailResponse;
import np.com.oskarshrestha.bookstorebackend.response.OrderResponse;
import np.com.oskarshrestha.bookstorebackend.response.OrdersResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public AddOrderResponse addOrder(AddOrderRequest addOrderRequest) {
        Orders order = new Orders();
        Optional<User> userOptional = userRepository.findByEmail(addOrderRequest.getEmail());
        if (userOptional.isPresent()) {
            List<Book> bookList = new ArrayList<>();
            addOrderRequest.getBookIdList().forEach(
                    id -> {
                        Optional<Book> bookOptional = bookRepository.findById(id);
                        bookOptional.ifPresent(bookList::add);
                    }
            );

            order.setUser(userOptional.get());
            order.setBooks(bookList);
            order.setDeliveryAddress(addOrderRequest.getDeliveryAddress());

            // Update the associations in the associated entities
            userOptional.get().getOrders().add(order);
            bookList.forEach(book -> book.getOrders().add(order));

            orderRepository.save(order);

            return AddOrderResponse
                    .builder()
                    .status(true)
                    .message("success")
                    .build();
        } else {
            return AddOrderResponse
                    .builder()
                    .status(false)
                    .message("email not found")
                    .build();
        }
    }

    @Override
    public OrdersResponse fetchAllOrder(int page, int length) {
        List<MinOrder> minOrderList = new ArrayList<>();
        List<Orders> ordersList = orderRepository.findAll(PageRequest.of(page, length)).getContent();
        ordersList.forEach(
                orders -> {
                    MinOrder minOrder = new MinOrder();
                    minOrder.setId(orders.getId());
                    minOrder.setMinUser(MinUser.fromUser(orders.getUser()));

                    List<MinBook> minBookList = new ArrayList<>();
                    AtomicInteger amount = new AtomicInteger();
                    orders.getBooks().forEach(
                            book -> {
                                amount.addAndGet(book.getPrice());
                                minBookList.add(MinBook.fromBook(book));
                            }
                    );
                    minOrder.setAmount(amount.get());
                    minOrder.setMinBookList(minBookList);
                    minOrder.setDeliveryAddress(orders.getDeliveryAddress());
                    minOrderList.add(minOrder);
                }
        );
        if (minOrderList.isEmpty()) {
            return OrdersResponse
                    .builder()
                    .status(false)
                    .message("no orders found")
                    .page(page)
                    .size(minOrderList.size())
                    .build();
        } else {
            return OrdersResponse
                    .builder()
                    .status(true)
                    .message("success")
                    .minOrderList(minOrderList)
                    .page(page)
                    .size(minOrderList.size())
                    .build();
        }
    }

    @Override
    public OrderResponse fetchOrder(long id) {
        Optional<Orders> orderOptional = orderRepository.findById(id);
        if (orderOptional.isPresent()) {
            Orders order = orderOptional.get();

            MinOrder minOrder = new MinOrder();
            minOrder.setId(order.getId());
            minOrder.setMinUser(MinUser.fromUser(order.getUser()));

            List<MinBook> minBookList = new ArrayList<>();
            AtomicInteger amount = new AtomicInteger();
            order.getBooks().forEach(
                    book -> {
                        amount.addAndGet(book.getPrice());
                        minBookList.add(MinBook.fromBook(book));
                    }
            );
            minOrder.setAmount(amount.get());
            minOrder.setMinBookList(minBookList);
            minOrder.setDeliveryAddress(order.getDeliveryAddress());
            return OrderResponse
                    .builder()
                    .status(true)
                    .message("success")
                    .minOrder(minOrder)
                    .build();
        } else {
            return OrderResponse
                    .builder()
                    .status(false)
                    .message("order id not found")
                    .minOrder(null)
                    .build();
        }
    }

    @Override
    public OrderByEmailResponse fetchOrderByEmail(String email) {
        Optional<User> userOptional = userRepository.findByEmail(email);

        if (userOptional.isEmpty()) {
            return OrderByEmailResponse.builder()
                    .status(false)
                    .message("Email not found")
                    .minOrderList(null)
                    .build();
        }

        List<Orders> ordersList = orderRepository.findByUserEmail(email);

        if (ordersList.isEmpty()) {
            return OrderByEmailResponse.builder()
                    .status(false)
                    .message("Empty orders")
                    .minOrderList(null)
                    .build();
        }

        List<MinOrder> minOrderList = new ArrayList<>();

        ordersList.forEach(order -> {
            MinOrder minOrder = new MinOrder();
            minOrder.setId(order.getId());
            minOrder.setMinUser(MinUser.fromUser(userOptional.get()));

            List<MinBook> minBookList = new ArrayList<>();
            AtomicInteger amount = new AtomicInteger();
            order.getBooks().forEach(book -> {
                amount.addAndGet(book.getPrice());
                minBookList.add(MinBook.fromBook(book));
            });
            minOrder.setAmount(amount.get());
            minOrder.setMinBookList(minBookList);
            minOrder.setDeliveryAddress(order.getDeliveryAddress());
            minOrderList.add(minOrder);
        });

        return OrderByEmailResponse.builder()
                .status(true)
                .message("success")
                .minOrderList(minOrderList)
                .build();
    }

}
