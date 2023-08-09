package np.com.oskarshrestha.bookstorebackend.service;

import np.com.oskarshrestha.bookstorebackend.response.DeleteBookResponse;
import np.com.oskarshrestha.bookstorebackend.response.MinUsersResponse;
import np.com.oskarshrestha.bookstorebackend.request.PutBookRequest;
import np.com.oskarshrestha.bookstorebackend.response.PutBookResponse;

public interface AdminService {

    DeleteBookResponse deleteBookById(Long id);

    PutBookResponse updateBookById(long id, PutBookRequest putBookRequest);

    MinUsersResponse fetchAllMinUser();
}
