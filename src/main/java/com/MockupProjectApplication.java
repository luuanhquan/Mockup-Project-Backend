package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication(exclude={SecurityAutoConfiguration.class})
@CrossOrigin("*")
//@EntityScan(basePackages = "com.entity")
//@EnableJpaRepositories(basePackages = {"com.repositories","com.service"})
public class MockupProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(MockupProjectApplication.class, args);
    }
}
