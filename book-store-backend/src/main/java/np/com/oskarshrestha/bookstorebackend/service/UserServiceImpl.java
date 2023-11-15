package np.com.oskarshrestha.bookstorebackend.service;

import io.jsonwebtoken.JwtException;
import lombok.extern.slf4j.Slf4j;
import np.com.oskarshrestha.bookstorebackend.entity.User;
import np.com.oskarshrestha.bookstorebackend.model.MinUser;
import np.com.oskarshrestha.bookstorebackend.repository.UserRepository;
import np.com.oskarshrestha.bookstorebackend.request.UserAuthenticationRequest;
import np.com.oskarshrestha.bookstorebackend.request.UserRegisterRequest;
import np.com.oskarshrestha.bookstorebackend.response.MinUserResponse;
import np.com.oskarshrestha.bookstorebackend.response.UserAuthenticationResponse;
import np.com.oskarshrestha.bookstorebackend.response.UserRegisterResponse;
import np.com.oskarshrestha.bookstorebackend.util.APIResponseStatus;
import np.com.oskarshrestha.bookstorebackend.util.ResponseModel;
import np.com.oskarshrestha.bookstorebackend.util.ResponseStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private APIResponseStatus apiResponseStatus;

    @Override
    public ResponseModel registerUser(UserRegisterRequest userRegisterRequest) {
        ResponseModel rs;
        try {
            if (userRepository.existsByEmail(userRegisterRequest.getEmail())) {
                rs = ResponseStatus.success(
                        apiResponseStatus.FAILURE_MESSAGE,
                        UserRegisterResponse
                                .builder()
                                .existingUser(true)
                                .registrationSuccess(false)
                                .build(),
                        HttpStatus.CONFLICT,
                        false
                );
            } else {
                User user = new User();
                user.setFirstName(userRegisterRequest.getFirstName());
                user.setLastName(userRegisterRequest.getLastName());
                user.setEmail(userRegisterRequest.getEmail());
                user.setRole(userRegisterRequest.getRole());
                user.setPassword(passwordEncoder.encode(userRegisterRequest.getPassword()));
                user.setEnabled(true);

                userRepository.save(user);

                rs = ResponseStatus.success(
                        apiResponseStatus.SUCCESS_MESSAGE,
                        UserRegisterResponse
                                .builder()
                                .existingUser(false)
                                .registrationSuccess(true)
                                .build(),
                        HttpStatus.OK,
                        true
                );
            }
        } catch (Exception e) {
            log.error("Exception: RegisterUser: " + Arrays.asList(e.getStackTrace()));
            rs = ResponseStatus.error(
                    apiResponseStatus.FAILURE_MESSAGE,
                    e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    false
            );
        }
        return rs;
    }

    @Override
    public ResponseModel authenticate(UserAuthenticationRequest userAuthenticationRequest) {
        ResponseModel rs = null;
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

            rs = ResponseStatus.success(
                    apiResponseStatus.SUCCESS_MESSAGE,
                    UserAuthenticationResponse.builder().token(jwtToken).build(),
                    HttpStatus.OK,
                    true
            );
        } catch (UsernameNotFoundException e) {
            log.error("Exception: Authenticate: " + e.getMessage());
            rs = ResponseStatus.error(
                    apiResponseStatus.FAILURE_MESSAGE,
                    e.getMessage(),
                    HttpStatus.NOT_FOUND,
                    false
            );
        } catch (AuthenticationException e) {
            log.error("Exception: Authenticate: " + e.getMessage());
            rs = ResponseStatus.error(
                    apiResponseStatus.FAILURE_MESSAGE,
                    e.getMessage(),
                    HttpStatus.UNAUTHORIZED,
                    false
            );
        } catch (Exception e) {
            log.error("Exception: Authenticate: " + e.getMessage());
            rs = ResponseStatus.error(
                    apiResponseStatus.FAILURE_MESSAGE,
                    e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    false
            );
        }
        return rs;
    }

    @Override
    public ResponseModel fetchMinUser() {
        ResponseModel rs;
        try {
            Authentication authenticationToken = SecurityContextHolder.getContext().getAuthentication();
            User user = (User) authenticationToken.getPrincipal();

            if (user != null) {
                rs = ResponseStatus.success(
                        apiResponseStatus.DATA_FOUND_MESSAGE,
                        MinUserResponse
                                .builder()
                                .status(true)
                                .message("success")
                                .minUser(MinUser.fromUser(user))
                                .build(),
                        HttpStatus.OK,
                        true);
            } else {
                rs = ResponseStatus.success(
                        apiResponseStatus.DATA_NOT_FOUND_MESSAGE,
                        MinUserResponse
                                .builder()
                                .status(false)
                                .message("email not found")
                                .minUser(null)
                                .build(),
                        HttpStatus.OK,
                        true);
            }
        } catch (Exception e) {
            log.error("Exception: FetchMinUser: " + Arrays.asList(e.getStackTrace()));
            rs = ResponseStatus.error(
                    apiResponseStatus.FAILURE_MESSAGE,
                    e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    false
            );
        }
        return rs;
    }
}
