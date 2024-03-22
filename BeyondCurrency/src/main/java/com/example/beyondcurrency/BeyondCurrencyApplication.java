package com.example.beyondcurrency;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

//@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@SpringBootApplication
public class BeyondCurrencyApplication {

    public static void main(String[] args) {
        SpringApplication.run(BeyondCurrencyApplication.class, args);
    }

}
