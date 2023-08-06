package np.com.oskarshrestha.bookstorebackend.service;

import np.com.oskarshrestha.bookstorebackend.model.DeleteBookResponse;
import np.com.oskarshrestha.bookstorebackend.model.MinUsersResponse;
import np.com.oskarshrestha.bookstorebackend.model.PutBookRequest;
import np.com.oskarshrestha.bookstorebackend.model.PutBookResponse;

public interface AdminService {

    DeleteBookResponse deleteBookById(Long id);

    PutBookResponse updateBookById(long id, PutBookRequest putBookRequest);

    MinUsersResponse fetchAllMinUser();
}
