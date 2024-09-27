package com.nus.iss.tasktracker.config;

import com.nus.iss.tasktracker.interceptor.TaskTrackerInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableWebMvc
@Configuration
@Slf4j
public class WebMvcConfig implements WebMvcConfigurer {

    private final TaskTrackerInterceptor taskTrackerInterceptor;

    @Autowired
    public WebMvcConfig(TaskTrackerInterceptor taskTrackerInterceptor) {
        this.taskTrackerInterceptor = taskTrackerInterceptor;
    }


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(taskTrackerInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/login"); // Exclude specific URL pattern
    }
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        System.out.println("addCorsMappings called");
        registry.addMapping("/**")  // Allows CORS requests to all endpoints
                .allowedOrigins("http://localhost:3000")  // Replace with the IP of your React app
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")  // Allowed HTTP methods
                .allowedHeaders("*")  // Allows all headers
                .allowCredentials(true);
    }

//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        log.info("CORS WORKING IN SERVER");
//        registry.addMapping("/**")
//                .allowedOrigins("http://165.22.100.234:3000, http://localhost:8080")
//                .allowedMethods("GET", "POST", "PUT", "DELETE, OPTIONS")
//                .allowedHeaders("*")
//                //.allowCredentials(true)
//                .maxAge(3600);
//    }
}