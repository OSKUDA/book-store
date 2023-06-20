package np.com.oskarshrestha.bookstorebackend.service;

import np.com.oskarshrestha.bookstorebackend.model.UserAuthenticationRequest;
import np.com.oskarshrestha.bookstorebackend.model.UserAuthenticationResponse;
import np.com.oskarshrestha.bookstorebackend.model.UserRegisterRequest;
import np.com.oskarshrestha.bookstorebackend.model.UserRegisterResponse;

public interface UserService {
    UserRegisterResponse registerUser(UserRegisterRequest userRegisterRequest);

    UserAuthenticationResponse authenticate(UserAuthenticationRequest userAuthenticationRequest);
}
