package com.example.banknator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BanknatorApplication implements CommandLineRunner {
    private final Logger logger = LoggerFactory.getLogger(BanknatorApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(BanknatorApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

    }
}
