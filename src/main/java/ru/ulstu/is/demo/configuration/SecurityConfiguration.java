package ru.ulstu.is.demo.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@Order(1000)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    private final Logger log = LoggerFactory.getLogger(SecurityConfiguration.class);

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        log.info("Creating security configuration");
        http.authorizeRequests().anyRequest().permitAll();
        http.csrf().disable();
    }
}
