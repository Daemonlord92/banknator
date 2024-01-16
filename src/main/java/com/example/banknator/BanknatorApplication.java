package com.example.banknator;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BanknatorApplication implements CommandLineRunner {
    private final Logger logger = LoggerFactory.getLogger(BanknatorApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(BanknatorApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

    }
    @Bean
    public OpenAPI springShopOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Banknator API")
                        .description("An spring banking application")
                        .version("v0.0.1")
                        .license(new License().name("Apache 2.0").url("http://springdoc.org")));
    }
}
