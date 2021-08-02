package com;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication(exclude={SecurityAutoConfiguration.class})
@CrossOrigin("*")
//@EntityScan(basePackages = "com.entity")
//@EnableJpaRepositories(basePackages = {"com.repositories","com.service"})


public class MockupProjectApplication {
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
    public static void main(String[] args) {
        SpringApplication.run(MockupProjectApplication.class, args);
    }
}
