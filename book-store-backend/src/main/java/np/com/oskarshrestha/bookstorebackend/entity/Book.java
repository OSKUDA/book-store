package np.com.oskarshrestha.bookstorebackend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Book extends Auditory{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String title;

    private String author;

    @Column(name = "publication_date")
    private int publicationDate;

    private String summary;

    private int quantity;

    private int price;

    // Establishing the many-to-many relationships
    @JsonIgnore
    @ManyToMany(mappedBy = "books")
    private List<Orders> orders = new ArrayList<>();

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", publicationDate=" + publicationDate +
                ", summary='" + summary + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                '}';
    }
}
