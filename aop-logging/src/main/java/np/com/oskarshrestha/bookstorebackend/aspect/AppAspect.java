package np.com.oskarshrestha.bookstorebackend.aspect;

import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * @author Oskar Krishna Shrestha
 * date: 10/20/2023
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