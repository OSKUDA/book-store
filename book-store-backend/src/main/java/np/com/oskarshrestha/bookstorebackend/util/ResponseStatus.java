package np.com.oskarshrestha.bookstorebackend.util;

import org.springframework.http.HttpStatus;

public final class ResponseStatus {

    private ResponseStatus() {

    }

    public static ResponseModel success(String message, Object data, HttpStatus httpStatus, boolean status) {
        ResponseModel rs = new ResponseModel();
        rs.setMessage(message);
        rs.setData(data);
        rs.setHttpStatus(httpStatus);
        rs.setStatusCode(httpStatus.value());
        rs.setStatus(status);
        return rs;
    }

    public static ResponseModel error(String message, Object errors, HttpStatus httpStatus, boolean status) {
        ResponseModel rs = new ResponseModel();
        rs.setMessage(message);
        rs.setHttpStatus(httpStatus);
        rs.setErrors(errors);
        rs.setStatusCode(httpStatus.value());
        rs.setStatus(status);
        return rs;
    }
}
