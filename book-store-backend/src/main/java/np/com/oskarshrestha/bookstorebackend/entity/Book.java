package np.com.oskarshrestha.bookstorebackend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Book {

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

    // Establishing the many-to-many relationship
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
