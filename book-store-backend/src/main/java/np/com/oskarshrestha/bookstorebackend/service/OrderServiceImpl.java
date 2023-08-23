package np.com.oskarshrestha.bookstorebackend.service;

import lombok.extern.slf4j.Slf4j;
import np.com.oskarshrestha.bookstorebackend.entity.Book;
import np.com.oskarshrestha.bookstorebackend.entity.Orders;
import np.com.oskarshrestha.bookstorebackend.entity.User;
import np.com.oskarshrestha.bookstorebackend.model.MinBook;
import np.com.oskarshrestha.bookstorebackend.model.MinOrder;
import np.com.oskarshrestha.bookstorebackend.model.MinUser;
import np.com.oskarshrestha.bookstorebackend.repository.BookRepository;
import np.com.oskarshrestha.bookstorebackend.repository.OrderRepository;
import np.com.oskarshrestha.bookstorebackend.repository.UserRepository;
import np.com.oskarshrestha.bookstorebackend.request.AddOrderRequest;
import np.com.oskarshrestha.bookstorebackend.response.AddOrderResponse;
import np.com.oskarshrestha.bookstorebackend.response.OrderByEmailResponse;
import np.com.oskarshrestha.bookstorebackend.response.OrderResponse;
import np.com.oskarshrestha.bookstorebackend.response.OrdersResponse;
import np.com.oskarshrestha.bookstorebackend.util.APIResponseStatus;
import np.com.oskarshrestha.bookstorebackend.util.ResponseModel;
import np.com.oskarshrestha.bookstorebackend.util.ResponseStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private APIResponseStatus apiResponseStatus;

    @Override
    public ResponseModel addOrder(AddOrderRequest addOrderRequest) {
        ResponseModel rs;
        try {
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
                rs = ResponseStatus.success(
                        apiResponseStatus.SUCCESS_MESSAGE,
                        AddOrderResponse
                                .builder(

                                )
                                .status(true)
                                .message("success")
                                .build(),
                        HttpStatus.OK,
                        true
                );
            } else {
                rs = ResponseStatus.success(
                        apiResponseStatus.DATA_NOT_FOUND_MESSAGE,
                        AddOrderResponse
                                .builder()
                                .status(false)
                                .message("email not found")
                                .build(),
                        HttpStatus.OK,
                        true
                );
            }
        } catch (Exception e) {
            log.error("Exception: addOrder: " + Arrays.asList(e.getStackTrace()));
            rs = ResponseStatus.error(
                    apiResponseStatus.FAILURE_MESSAGE,
                    e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    false
            );
        }
        return rs;
    }

    @Override
    public ResponseModel fetchAllOrder(int page, int length) {
        ResponseModel rs;
        try {
            List<MinOrder> minOrderList = new ArrayList<>();
            List<Orders> ordersList = orderRepository.findAll(PageRequest.of(page, length)).getContent();
            if (ordersList.size() > 0) {
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
                        });
                rs = ResponseStatus.success(
                        apiResponseStatus.DATA_FOUND_MESSAGE,
                        OrdersResponse
                                .builder()
                                .status(true)
                                .message("success")
                                .minOrderList(minOrderList)
                                .page(page)
                                .size(minOrderList.size())
                                .build(),
                        HttpStatus.OK,
                        true
                );
            } else {
                rs = ResponseStatus.success(
                        apiResponseStatus.DATA_NOT_FOUND_MESSAGE,
                        OrdersResponse
                                .builder()
                                .status(false)
                                .message("no orders found")
                                .page(page)
                                .size(0)
                                .build(),
                        HttpStatus.OK,
                        true
                );
            }
        } catch (Exception e) {
            log.error("Exception: fetchAllOrder: " + Arrays.asList(e.getStackTrace()));
            rs = ResponseStatus.error(
                    apiResponseStatus.FAILURE_MESSAGE,
                    e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    false
            );
        }
        return rs;
    }

    @Override
    public ResponseModel fetchOrder(long id) {
        ResponseModel rs;
        try {
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
                rs = ResponseStatus.success(
                        apiResponseStatus.DATA_FOUND_MESSAGE,
                        OrderResponse
                                .builder()
                                .status(true)
                                .message("success")
                                .minOrder(minOrder)
                                .build(),
                        HttpStatus.OK,
                        true
                );
            } else {
                rs = ResponseStatus.success(
                        apiResponseStatus.DATA_NOT_FOUND_MESSAGE,
                        OrderResponse
                                .builder()
                                .status(false)
                                .message("order id not found")
                                .minOrder(null)
                                .build(),
                        HttpStatus.OK,
                        true);
            }
        } catch (Exception e) {
            log.error("Exception: fetchOrder: " + Arrays.asList(e.getStackTrace()));
            rs = ResponseStatus.error(
                    apiResponseStatus.FAILURE_MESSAGE,
                    e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    false
            );
        }
        return rs;
    }

    @Override
    public ResponseModel fetchOrderByEmail(String email) {
        ResponseModel rs;
        try {
            User user = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("email not found"));
            List<Orders> ordersList = orderRepository.findByUserEmail(email);
            if (ordersList.size() > 0) {
                List<MinOrder> minOrderList = new ArrayList<>();

                ordersList.forEach(order -> {
                    MinOrder minOrder = new MinOrder();
                    minOrder.setId(order.getId());
                    minOrder.setMinUser(MinUser.fromUser(user));

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

                rs = ResponseStatus.success(
                        apiResponseStatus.DATA_FOUND_MESSAGE,
                        OrderByEmailResponse.builder()
                                .status(true)
                                .message("success")
                                .minOrderList(minOrderList)
                                .build(),
                        HttpStatus.OK,
                        true
                );
            } else {
                rs = ResponseStatus.success(
                        apiResponseStatus.DATA_NOT_FOUND_MESSAGE,
                        OrderByEmailResponse.builder()
                                .status(false)
                                .message("Empty orders")
                                .minOrderList(null)
                                .build(),
                        HttpStatus.OK,
                        true
                );
            }
        } catch (UsernameNotFoundException e) {
            rs = ResponseStatus.success(
                    apiResponseStatus.DATA_NOT_FOUND_MESSAGE,
                    OrderByEmailResponse.builder()
                            .status(false)
                            .message("Email not found")
                            .minOrderList(null)
                            .build(),
                    HttpStatus.OK,
                    true
            );
        } catch (Exception e) {
            log.error("Exception: fetchOrderByEmail: " + Arrays.asList(e.getStackTrace()));
            rs = ResponseStatus.error(
                    apiResponseStatus.FAILURE_MESSAGE,
                    e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    false
            );
        }
        return rs;
    }

}
