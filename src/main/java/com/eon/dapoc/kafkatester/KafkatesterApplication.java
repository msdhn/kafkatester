package com.eon.dapoc.kafkatester;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class KafkatesterApplication {

    public static void main(String[] args) {
        SpringApplication.run(KafkatesterApplication.class, args);
    }

}
