package np.com.oskarshrestha.bookstorebackend.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MinOrder {
    private Long id;

    private MinUser minUser;

    private List<MinBook> minBookList;
    private String deliveryAddress;
    private int amount;
}
