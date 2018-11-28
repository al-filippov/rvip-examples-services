package ru.ulstu.is.demo.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.ulstu.is.demo.validation.UserValidation;

@Configuration
public class ValidationConfiguration {
    @Bean
    public UserValidation getUserValidation() {
        return new UserValidation();
    }
}
