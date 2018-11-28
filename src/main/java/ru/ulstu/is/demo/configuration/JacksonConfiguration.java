package ru.ulstu.is.demo.configuration;

import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import com.fasterxml.jackson.module.afterburner.AfterburnerModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JacksonConfiguration {
    private final Logger log = LoggerFactory.getLogger(JacksonConfiguration.class);

    @Bean
    @ConditionalOnClass(name = "org.hibernate.SessionFactory")
    public Hibernate5Module hibernate5Module() {
        log.info("Creating Jackson Hibernate5Module bean");
        return new Hibernate5Module();
    }

    @Bean
    public AfterburnerModule afterburnerModule() {
        log.info("Creating Jackson AfterburnerModule bean");
        return new AfterburnerModule();
    }
}
