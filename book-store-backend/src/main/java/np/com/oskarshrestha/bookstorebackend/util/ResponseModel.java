package np.com.oskarshrestha.bookstorebackend.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseModel {

    private int statusCode;

    private String message;

    private HttpStatus httpStatus;

    private Object errors;

    private Object data;

    private boolean status;

}
