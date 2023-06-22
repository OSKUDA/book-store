package np.com.oskarshrestha.bookstorebackend.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import np.com.oskarshrestha.bookstorebackend.entity.Book;
import np.com.oskarshrestha.bookstorebackend.entity.User;
import np.com.oskarshrestha.bookstorebackend.util.Role;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MinUser {
    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private Role role;

    public static MinUser fromUser(User user) {
        return new MinUser(user.getId(), user.getEmail(), user.getFirstName(), user.getLastName(), user.getRole());
    }
}
