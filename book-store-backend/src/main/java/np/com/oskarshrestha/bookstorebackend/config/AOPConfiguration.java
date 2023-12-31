package np.com.oskarshrestha.bookstorebackend.config;

import np.com.oskarshrestha.aop.logging.AopLoggingHelper;
import org.springframework.aop.Advisor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * @author Oskar Krishna Shrestha
 * date: 11/13/2023
 * package: np.com.oskarshrestha.bookstorebackend.config
 */
@Configuration
public class AOPConfiguration {

    @Value("${aop.logging.include.pointcuts:}")
    List<String> includePointcuts;

    @Value("${aop.logging.exclude.pointcuts:}")
    List<String> excludePointcuts;

    @Bean
    public Advisor advisorBean() {
        return AopLoggingHelper.getAdvisor(includePointcuts, excludePointcuts);
    }

}
