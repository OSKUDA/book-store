package np.com.oskarshrestha.bookstorebackend.service;

import np.com.oskarshrestha.bookstorebackend.model.UserRegisterRequest;
import np.com.oskarshrestha.bookstorebackend.model.UserRegisterResponse;

public interface UserService {
    UserRegisterResponse registerUser(UserRegisterRequest userRegisterRequest);
}
