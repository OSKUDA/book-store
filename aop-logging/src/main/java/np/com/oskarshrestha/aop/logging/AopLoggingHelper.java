package np.com.oskarshrestha.aop.logging;

import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;

import java.util.List;

/**
 * @author Oskar Krishna Shrestha
 * date: 11/15/2023
 * package: np.com.oskarshrestha.aop.logging
 */
public class AopLoggingHelper {

    public static Advisor getAdvisor(List<String> includePointcuts, List<String> excludePointcuts) {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        if (!includePointcuts.isEmpty()) {
            String expression = "(" + String.join(" || ", includePointcuts) + ")";
            expression += (!excludePointcuts.isEmpty()) ? " && (!" + String.join(" || !", excludePointcuts) + ")" : "";
            pointcut.setExpression(expression);
            return new DefaultPointcutAdvisor(pointcut, new MethodInterceptorImpl());
        }
        pointcut.setExpression("execution(* example.com..*(..))");
        return new DefaultPointcutAdvisor(pointcut, new MethodInterceptorImpl());
    }
}
