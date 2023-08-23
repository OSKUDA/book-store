package np.com.oskarshrestha.bookstorebackend.service;

import np.com.oskarshrestha.bookstorebackend.request.PutBookRequest;
import np.com.oskarshrestha.bookstorebackend.util.ResponseModel;

public interface AdminService {

    ResponseModel deleteBookById(Long id);

    ResponseModel updateBookById(long id, PutBookRequest putBookRequest);

    ResponseModel fetchAllMinUser();
}
