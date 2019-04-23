package com.frostinteractive.loginservice.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.frostinteractive.loginservice.repository")
@ComponentScan(basePackages = "com.frostinteractive.loginservice")
@EntityScan(basePackages = "com.frostinteractive.loginservice.domain")
@Import(value = {SecurityCredentialsConfig.class,CorsConfig.class})
public class LoginServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(LoginServiceApplication.class, args);
    }

}
