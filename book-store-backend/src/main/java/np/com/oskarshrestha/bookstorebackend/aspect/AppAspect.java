package np.com.oskarshrestha.bookstorebackend.aspect;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * @author Oskar Krishna Shrestha
 * date: 10/17/2023
 * package: np.com.oskarshrestha.bookstorebackend.aspect
 */
@Aspect
@Component
@Slf4j
public class AppAspect {
    private String ipAddress = "[NO-IP]";

    @Before("execution(* np.com.oskarshrestha.bookstorebackend.controller..*(..)) " +
            "|| execution(* np.com.oskarshrestha.bookstorebackend.service..*(..)) " +
            "|| execution(* np.com.oskarshrestha.bookstorebackend.repository..*(..))")
    public void logBeforeMethodExecution(JoinPoint joinPoint) {
        try {
            // get ip address
            ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            if (requestAttributes != null) {
                HttpServletRequest request = requestAttributes.getRequest();
                ipAddress = request.getRemoteAddr();
            }
            // get method name
            String methodName = joinPoint.getSignature().getDeclaringType().getPackage().getName() + "." + joinPoint.getSignature().getName();
            log.info("[{}] Started method execution {}", ipAddress, methodName);
        } catch (Exception e) {
            log.error("{} Error in AppAspect {}", ipAddress, e.getMessage());
        }
    }

    @AfterReturning("execution(* np.com.oskarshrestha.bookstorebackend.controller..*(..)) " +
            "|| execution(* np.com.oskarshrestha.bookstorebackend.service..*(..)) " +
            "|| execution(* np.com.oskarshrestha.bookstorebackend.repository..*(..))")
    public void logAfterMethodExecution(JoinPoint joinPoint) {
        try {
            // get method name
            String methodName = joinPoint.getSignature().getDeclaringType().getPackage().getName() + "." + joinPoint.getSignature().getName();
            log.info("[{}] Completed method execution {}", ipAddress, methodName);
        } catch (Exception e) {
            log.error("{} Error in AppAspect {}", ipAddress, e.getMessage());
        }
    }

    @AfterThrowing(value = "execution(* np.com.oskarshrestha.bookstorebackend.controller..*(..)) " +
            "|| execution(* np.com.oskarshrestha.bookstorebackend.service..*(..)) " +
            "|| execution(* np.com.oskarshrestha.bookstorebackend.repository..*(..))", throwing = "exception")
    public void logAfterMethodExecutionThrows(JoinPoint joinPoint, Exception exception) {
        try {
            // get method name
            String methodName = joinPoint.getSignature().getDeclaringType().getPackage().getName() + "." + joinPoint.getSignature().getName();
            log.info("[{}] Exception thrown by method execution {} {}", ipAddress, methodName, exception.getMessage());
        } catch (Exception e) {
            log.error("{} Error in AppAspect {}", ipAddress, e.getMessage());
        }
    }
}
