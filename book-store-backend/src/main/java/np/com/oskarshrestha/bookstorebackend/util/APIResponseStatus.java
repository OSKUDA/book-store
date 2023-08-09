package np.com.oskarshrestha.bookstorebackend.util;

import org.springframework.stereotype.Component;

@Component
public class APIResponseStatus {

    public final String SUCCESS_MESSAGE = "Success";

    public final String FAILURE_MESSAGE = "Failure";

    public final String DATA_FOUND_MESSAGE = "Data Found";

    public final String DATA_NOT_FOUND_MESSAGE = "No Data Found";

}
