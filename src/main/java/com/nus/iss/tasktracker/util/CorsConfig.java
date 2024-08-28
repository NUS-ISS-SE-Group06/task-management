package com.nus.iss.tasktracker.util;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class CorsConfig{


//    public WebMvcConfigurer corsConfig(){
//        return new WebMvcConfigurer(){
//            @Override
//            public void addCorsMappings(CorsRegistry registry) {
//                registry.addMapping("/**")
//                        .allowedOrigins("http://165.22.100.234:3000/", "http://localhost:3000") // Adjust the origin of your frontend application
//                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
//                        .allowedHeaders("Content-Type", "Accept", "Authorization")
//                        .allowCredentials(true)
//                        .maxAge(3600);
//            }
//        };
//    }

}
