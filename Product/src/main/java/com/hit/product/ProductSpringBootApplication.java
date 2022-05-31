package com.hit.product;

import com.hit.product.applications.services.VerificationTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.Arrays;

@SpringBootApplication
@EnableScheduling
public class ProductSpringBootApplication {


    public static void main(String[] args) {
        SpringApplication.run(ProductSpringBootApplication.class, args);
    }

}
