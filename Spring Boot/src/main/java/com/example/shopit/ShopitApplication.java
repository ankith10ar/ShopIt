package com.example.shopit;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication(scanBasePackages = {"com.example"}  )
@EnableEncryptableProperties
@PropertySource(name="EncryptedProperties", value = "classpath:encrypted.properties")
public class ShopitApplication extends SpringBootServletInitializer {

    private static Class applicationClass = ShopitApplication.class;

    public static void main(String[] args) {
        //System.setProperty("jasypt.encryptor.password", "password");
        SpringApplication.run(ShopitApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(applicationClass);
    }
}
