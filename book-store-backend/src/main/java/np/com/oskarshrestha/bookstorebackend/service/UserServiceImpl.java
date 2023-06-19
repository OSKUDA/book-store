package np.com.oskarshrestha.bookstorebackend.service;

import np.com.oskarshrestha.bookstorebackend.entity.User;
import np.com.oskarshrestha.bookstorebackend.model.UserRegisterRequest;
import np.com.oskarshrestha.bookstorebackend.model.UserRegisterResponse;
import np.com.oskarshrestha.bookstorebackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public UserRegisterResponse registerUser(UserRegisterRequest userRegisterRequest){
        if(userRepository.existsByEmail(userRegisterRequest.getEmail())){
            return UserRegisterResponse
                    .builder()
                    .existingUser(true)
                    .registrationSuccess(false)
                    .build();
        }

        User user = new User();
        user.setFirstName(userRegisterRequest.getFirstName());
        user.setLastName(userRegisterRequest.getLastName());
        user.setEmail(userRegisterRequest.getEmail());
        user.setRole(userRegisterRequest.getRole());
        user.setPassword(passwordEncoder.encode(userRegisterRequest.getPassword()));
        user.setEnabled(true);

        userRepository.save(user);

        return UserRegisterResponse
                .builder()
                .existingUser(false)
                .registrationSuccess(true)
                .build();
    }

}
