package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @description:
 * @author: shurunlong
 * @create: 2020-08-05 11:29
 */
@EnableScheduling
@SpringBootApplication
@EnableCaching
public class OptionalApplication {
    public static void main(String[] args) {
        SpringApplication.run(OptionalApplication.class, args);
    }
}
