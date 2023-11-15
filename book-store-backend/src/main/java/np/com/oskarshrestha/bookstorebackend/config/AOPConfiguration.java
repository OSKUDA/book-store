package np.com.oskarshrestha.bookstorebackend.config;

import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
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

    @Value("${aop.logging.pointcuts}")
    List<String> pointcuts;

    @Bean
    public Advisor advisorBean() {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression(String.join(" || ", pointcuts));
        return new DefaultPointcutAdvisor(pointcut, new MyMethodInterceptor());
    }

}
