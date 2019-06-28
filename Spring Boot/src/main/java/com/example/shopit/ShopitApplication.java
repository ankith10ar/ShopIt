package com.example.shopit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(scanBasePackages = {"com.example"}  )
public class ShopitApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShopitApplication.class, args);
    }

}
