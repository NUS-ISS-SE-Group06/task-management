package com.nus.iss.tasktracker.config;
import com.nus.iss.tasktracker.filter.TaskTrackerFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class FilterConfig {

    private final TaskTrackerFilter taskTrackerFilter;

    @Autowired
    public FilterConfig(TaskTrackerFilter taskTrackerFilter) {
        this.taskTrackerFilter = taskTrackerFilter;
    }

    @Bean
    public FilterRegistrationBean<TaskTrackerFilter> myFilterRegistration() {
        FilterRegistrationBean<TaskTrackerFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(taskTrackerFilter);
        registrationBean.addUrlPatterns("/*"); // URL patterns to which the filter should apply
        return registrationBean;
    }
}
