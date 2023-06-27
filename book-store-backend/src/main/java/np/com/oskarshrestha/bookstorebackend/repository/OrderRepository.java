package np.com.oskarshrestha.bookstorebackend.repository;

import np.com.oskarshrestha.bookstorebackend.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Orders, Long> {
    List<Orders> findByUserEmail(String email);
}
