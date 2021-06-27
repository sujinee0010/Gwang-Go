package com.example.gwanggo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.stereotype.Service;


@SpringBootApplication
public class GwanggoApplication {

    public static void main(String[] args) {
        SpringApplication.run(GwanggoApplication.class, args);
    }

}
