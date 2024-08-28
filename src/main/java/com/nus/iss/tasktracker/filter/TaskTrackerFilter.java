package com.nus.iss.tasktracker.filter;
import com.nus.iss.tasktracker.util.JWTUtil;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.IOException;

@Slf4j
@Component
public class TaskTrackerFilter  implements Filter {

    private final JWTUtil jwtUtil;

    @Autowired
    public TaskTrackerFilter(JWTUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Initialization logic, if needed
        log.info("TaskTrackerFilter: init called");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        // Pre-processing logic
        log.info("TaskTrackerFilter: Executing pre-processing logic in the filter {} {}", servletRequest, servletResponse);
        boolean isTokenValid = true;

        try {
            String token = null;
            // This method is called before the controller method is invoked.
            // You can perform pre-processing here.
            System.out.println("TaskTrackerFilter PRINTED");

            HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
            // Retrieve a specific header
            String headerValue = httpRequest.getHeader("Authorization");
            // Do something with the header value

            if(headerValue!=null){
                token = headerValue;
                System.out.println("Authorization Header Value: " + token);
                log.info("Authorization header value: {}", token);
                token = token.replaceAll("Bearer ", "");
            }

            // DO TOKEN VALIDATION
            // THROW ERROR IF THE TOKEN IS EMPTY OR THE VALIDATION FAILS
            if(!StringUtils.hasText(token)){
                log.warn("TOKEN IS EMPTY");
                System.out.println("TOKEN IS EMPTY");
                // FIXME
                //throw new Exception("No Token");
            } else{
                log.info("TOKEN IS {}",token);
                String[] subjectRoleValues = jwtUtil.validateJWT(token);
                if((subjectRoleValues==null) || (subjectRoleValues.length!=2) ||
                        (!StringUtils.hasText(subjectRoleValues[0])) || (!StringUtils.hasText(subjectRoleValues[1]))){
                    isTokenValid = false;
                }
            }
            log.info("TaskTrackerFilter Token valid: {}",isTokenValid);
            System.out.println("TaskTrackerFilter valid: {}: " + token);

            if(!isTokenValid){
                System.out.println("Auth Token Validation Failed");
                throw new RuntimeException("Auth Token Validation Failed");
            }

        } catch(Exception exception){
            log.error("Exception {}", exception.getMessage());
            System.out.println("Exception in validating token: "+exception.getMessage());
        }
        // Continue the filter chain
        filterChain.doFilter(servletRequest, servletResponse);

        // Post-processing logic
        log.info("TaskTrackerFilter: Executing post-processing logic in the filter");
    }

    @Override
    public void destroy() {
        // Cleanup logic, if needed
        log.info("TaskTrackerFilter: destroy called");
    }
}
