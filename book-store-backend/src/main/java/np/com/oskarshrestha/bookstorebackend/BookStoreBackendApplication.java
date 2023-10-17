package np.com.oskarshrestha.bookstorebackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class BookStoreBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookStoreBackendApplication.class, args);
    }

}
