package np.com.oskarshrestha.bookstorebackend.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Establishing the many-to-one relationship
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    // Establishing the many-to-many relationship
    @ManyToMany
    @JoinTable(
            name = "order_books",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id")
    )
    private List<Book> books = new ArrayList<>();

    private String deliveryAddress;

}
