package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Component;

@SpringBootApplication(exclude={SecurityAutoConfiguration.class})
@EntityScan(basePackages = "com.entity")
@EnableJpaRepositories(basePackages = {"com.repositories","com.service"})
public class MockupProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(MockupProjectApplication.class, args);
    }
}
