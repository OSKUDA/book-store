package np.com.oskarshrestha.aop.logging;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * @author Oskar Krishna Shrestha
 * date: 11/15/2023
 * package: np.com.oskarshrestha.aop.logging
 */
@Slf4j
public class MethodInterceptorImpl implements MethodInterceptor {
    private final ObjectMapper objectMapper = getObjectMapper();

    private ObjectMapper getObjectMapper() {
        // Create an ObjectMapper with Java 8 date/time support
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.registerModule(new Hibernate5Module());
        // log a message to indicate that object mapper is created
        log.info("Created ObjectMapper with Java 8 date/time & Hibernate5 support");
        return mapper;
    }


    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        Object returnObject;
        String remoteIpAddress = "-";
        String xForwardedForIpAddress = "-";
        long startTime;

        String methodName = invocation.getMethod().getDeclaringClass().getPackage().getName() + "." + invocation.getMethod().getDeclaringClass().getSimpleName() + "." + invocation.getMethod().getName();
        try {
            // Before method invocation
            startTime = System.currentTimeMillis();
            ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            if (requestAttributes != null) {
                HttpServletRequest request = requestAttributes.getRequest();
                remoteIpAddress = request.getRemoteAddr();
                xForwardedForIpAddress = request.getHeader("X-Forwarded-For");
            }
            log.info("[{},{}] Started executing method: {}", remoteIpAddress, xForwardedForIpAddress, methodName);
            Object[] methodArguments = invocation.getArguments();
            if (methodArguments.length > 0) {
                try {
                    long payloadToJsonTimeStart = System.currentTimeMillis();
                    String payload = objectMapper.writeValueAsString(methodArguments[0]);
                    log.info("[{},{}] Converted payload to JSON in {} ms : {}", remoteIpAddress, xForwardedForIpAddress, System.currentTimeMillis() - payloadToJsonTimeStart, payload);
                } catch (JsonProcessingException e) {
                    log.error("[{},{}] Error converting payload to JSON: {}", remoteIpAddress, xForwardedForIpAddress, e.getMessage());
                }
            }
            // Method invocation
            returnObject = invocation.proceed();

            // After method invocation
            log.info("[{},{}] Completed execution of method: {}", remoteIpAddress, xForwardedForIpAddress, methodName);
            if (returnObject != null) {
                try {
                    long payloadToJsonTimeStart = System.currentTimeMillis();
                    String payload = objectMapper.writeValueAsString(returnObject);
                    log.info("[{},{}] Converted payload to JSON in {} ms : {}", remoteIpAddress, xForwardedForIpAddress, System.currentTimeMillis() - payloadToJsonTimeStart, payload);
                } catch (JsonProcessingException e) {
                    log.error("[{},{}] Error converting payload to JSON: {}", remoteIpAddress, xForwardedForIpAddress, e.getMessage());
                }
            } else {
                log.info("[{},{}] No response found for the method {}", remoteIpAddress, xForwardedForIpAddress, methodName);
            }
            log.info("[{},{}] Method execution time: {} ms", remoteIpAddress, xForwardedForIpAddress, System.currentTimeMillis() - startTime);
        } catch (Exception e) {
            log.info("[{},{}] Exception occurred for the method: {} {}", remoteIpAddress, xForwardedForIpAddress, methodName, e.getMessage());
            throw e;
        }
        return returnObject;
    }
}
