package np.com.oskarshrestha.bookstorebackend.service;

import np.com.oskarshrestha.bookstorebackend.request.UserAuthenticationRequest;
import np.com.oskarshrestha.bookstorebackend.request.UserRegisterRequest;
import np.com.oskarshrestha.bookstorebackend.response.MinUserResponse;
import np.com.oskarshrestha.bookstorebackend.response.UserAuthenticationResponse;
import np.com.oskarshrestha.bookstorebackend.response.UserRegisterResponse;

public interface UserService {
    UserRegisterResponse registerUser(UserRegisterRequest userRegisterRequest);

    UserAuthenticationResponse authenticate(UserAuthenticationRequest userAuthenticationRequest);

    MinUserResponse fetchMinUser(String email);

}
