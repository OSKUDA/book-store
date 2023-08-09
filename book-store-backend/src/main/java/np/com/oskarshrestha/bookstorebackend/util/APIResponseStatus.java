package np.com.oskarshrestha.bookstorebackend.util;

public class APIResponseStatus {

    private static APIResponseStatus instance;

    public final String SUCCESS_MESSAGE = "Success";

    public final String FAILURE_MESSAGE = "Failure";

    public final String DATA_FOUND_MESSAGE = "Data Found";

    public final String DATA_NOT_FOUND_MESSAGE = "No Data Found";

    private APIResponseStatus(){

    }

    public static synchronized APIResponseStatus getInstance(){
        if(instance == null){
            instance = new APIResponseStatus();
        }
        return instance;
    }

}
