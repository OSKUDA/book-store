package np.com.oskarshrestha.bookstorebackend.service;

import np.com.oskarshrestha.bookstorebackend.request.UserAuthenticationRequest;
import np.com.oskarshrestha.bookstorebackend.request.UserRegisterRequest;
import np.com.oskarshrestha.bookstorebackend.util.ResponseModel;

public interface UserService {
    ResponseModel registerUser(UserRegisterRequest userRegisterRequest);

    ResponseModel authenticate(UserAuthenticationRequest userAuthenticationRequest);

    ResponseModel fetchMinUser();

}
