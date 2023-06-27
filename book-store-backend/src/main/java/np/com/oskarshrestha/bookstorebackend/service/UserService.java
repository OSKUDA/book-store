package np.com.oskarshrestha.bookstorebackend.service;

import np.com.oskarshrestha.bookstorebackend.model.*;

public interface UserService {
    UserRegisterResponse registerUser(UserRegisterRequest userRegisterRequest);

    UserAuthenticationResponse authenticate(UserAuthenticationRequest userAuthenticationRequest);

    MinUserResponse fetchMinUser(String email);
}
