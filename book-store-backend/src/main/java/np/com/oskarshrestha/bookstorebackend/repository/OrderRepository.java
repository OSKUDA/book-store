package np.com.oskarshrestha.bookstorebackend.repository;

import np.com.oskarshrestha.bookstorebackend.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Orders, Long> {
}
