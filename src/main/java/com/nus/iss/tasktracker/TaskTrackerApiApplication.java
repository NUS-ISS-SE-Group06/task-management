package com.nus.iss.tasktracker;

import com.nus.iss.tasktracker.util.TaskTrackerConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@Slf4j
public class TaskTrackerApiApplication {
	public static void main(String[] args) {
		System.out.println("Task Tracker Application starting...");
		SpringApplication.run(TaskTrackerApiApplication.class, args);
		System.out.println("Task Tracker Application started successfully...");
	}

	@Bean
	public WebMvcConfigurer corsConfigurer(){

		return new WebMvcConfigurer() {
			@Override
			public void  addCorsMappings (CorsRegistry registry){
				registry.addMapping("/**")
						.allowedOrigins(TaskTrackerConstant.CROSS_ORIGIN_LOCALHOST_URL)
						.allowedMethods("GET","POST", "PUT", "DELETE", "OPTIONS")
						.allowedHeaders("*")
						.allowCredentials(true);
			}
		};
	}
}
