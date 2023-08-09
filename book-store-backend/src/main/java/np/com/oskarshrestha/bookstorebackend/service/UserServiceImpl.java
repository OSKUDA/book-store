package np.com.oskarshrestha.bookstorebackend.service;

import np.com.oskarshrestha.bookstorebackend.entity.User;
import np.com.oskarshrestha.bookstorebackend.model.*;
import np.com.oskarshrestha.bookstorebackend.repository.UserRepository;
import np.com.oskarshrestha.bookstorebackend.request.UserAuthenticationRequest;
import np.com.oskarshrestha.bookstorebackend.request.UserRegisterRequest;
import np.com.oskarshrestha.bookstorebackend.response.MinUserResponse;
import np.com.oskarshrestha.bookstorebackend.response.UserAuthenticationResponse;
import np.com.oskarshrestha.bookstorebackend.response.UserRegisterResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public UserRegisterResponse registerUser(UserRegisterRequest userRegisterRequest) {
        if (userRepository.existsByEmail(userRegisterRequest.getEmail())) {
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

    @Override
    public UserAuthenticationResponse authenticate(UserAuthenticationRequest userAuthenticationRequest) {
        try {
            User user = userRepository
                    .findByEmail(userAuthenticationRequest.getEmail())
                    .orElseThrow(
                            () -> new UsernameNotFoundException("Email not found: " + userAuthenticationRequest.getEmail())
                    );
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            userAuthenticationRequest.getEmail(),
                            userAuthenticationRequest.getPassword()
                    )
            );
            String jwtToken = jwtService.generateToken(user.toMap(), user);
            return UserAuthenticationResponse
                    .builder()
                    .token(jwtToken)
                    .errorMessage(null)
                    .build();
        } catch (Exception e) {
            return UserAuthenticationResponse
                    .builder()
                    .token(null)
                    .errorMessage(e.getMessage())
                    .build();
        }
    }

    @Override
    public MinUserResponse fetchMinUser(String email) {
        Optional<User> userOptional = userRepository.findByEmail(email);

        if (userOptional.isPresent()) {
            return MinUserResponse
                    .builder()
                    .status(true)
                    .message("success")
                    .minUser(MinUser.fromUser(userOptional.get()))
                    .build();
        } else {
            return MinUserResponse
                    .builder()
                    .status(false)
                    .message("email not found")
                    .minUser(null)
                    .build();
        }


    }

}
