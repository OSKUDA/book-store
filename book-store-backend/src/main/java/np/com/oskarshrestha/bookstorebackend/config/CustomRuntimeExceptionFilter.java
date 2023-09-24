package np.com.oskarshrestha.bookstorebackend.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import np.com.oskarshrestha.bookstorebackend.util.APIResponseStatus;
import np.com.oskarshrestha.bookstorebackend.util.ResponseModel;
import np.com.oskarshrestha.bookstorebackend.util.ResponseStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@Slf4j
public class CustomRuntimeExceptionFilter extends OncePerRequestFilter {

    @Autowired
    APIResponseStatus apiResponseStatus;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            doFilter(request, response, filterChain);
        } catch (ExpiredJwtException e) {
            log.error("ExpiredJwtException: {}", e.getMessage());
            setJwtExceptionResponse(apiResponseStatus.JWT_TOKEN_EXPIRED, response, e);
        } catch (JwtException e) {
            log.error("JwtException: {}", e.getMessage());
            setJwtExceptionResponse(apiResponseStatus.JWT_TOKEN_FAILURE, response, e);
        }
    }

    private void setJwtExceptionResponse(String error, HttpServletResponse response, Throwable e) {
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType("application/json");
        ResponseModel responseModel = ResponseStatus.error(
                error,
                e.getMessage(),
                HttpStatus.UNAUTHORIZED,
                false
        );
        ObjectMapper mapper = new ObjectMapper();
        try {
            response.getWriter().write(mapper.writeValueAsString(responseModel));
        } catch (IOException ex) {
            log.error("setJwtExceptionResponse : {}", ex.getMessage());
        }
    }
}
