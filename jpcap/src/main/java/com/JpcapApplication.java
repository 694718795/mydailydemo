package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
@EnableCaching
// https://blog.csdn.net/qq_37638061/article/details/80710143
//jpcap
public class JpcapApplication {

    public static void main(String[] args) {
        SpringApplication.run(JpcapApplication.class, args);
    }

}
