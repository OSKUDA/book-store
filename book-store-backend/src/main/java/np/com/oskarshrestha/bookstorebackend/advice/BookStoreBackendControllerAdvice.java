package np.com.oskarshrestha.bookstorebackend.advice;

import np.com.oskarshrestha.bookstorebackend.util.ResponseModel;
import np.com.oskarshrestha.bookstorebackend.util.ResponseStatus;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Oskar Krishna Shrestha
 * date: 11/14/2023
 * package: np.com.oskarshrestha.bookstorebackend.advice
 */
@ControllerAdvice
@SuppressWarnings({"unchecked","rawtypes"})
public class BookStoreBackendControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler({UsernameNotFoundException.class})
    public final ResponseEntity<ResponseModel> handleUsernameNotFoundException(Exception ex, WebRequest request) {
        ResponseModel rs = ResponseStatus.error(ex.getMessage(),
                ex.getLocalizedMessage(),
                HttpStatus.NOT_FOUND,
                false);
        return new ResponseEntity(rs, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({HttpServerErrorException.InternalServerError.class})
    public final ResponseEntity<ResponseModel> handleInternalServerException(Exception ex, WebRequest request) {
        ResponseModel rs = ResponseStatus.error(ex.getMessage(),
                ex.getLocalizedMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR,
                false);
        return new ResponseEntity(rs, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        Map<String, Object> errorMap = new HashMap<>();
        for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
            errorMap.put(fieldError.getField(), ex.getBindingResult().getFieldErrors(fieldError.getField()).stream()
                    .map(FieldError::getDefaultMessage)
                    .toList());
        }
        ResponseModel rs = ResponseStatus.error("Parameter Mismatched",
                errorMap,
                HttpStatus.BAD_REQUEST,
                false);
        return new ResponseEntity(rs, HttpStatus.BAD_REQUEST);
    }

}
