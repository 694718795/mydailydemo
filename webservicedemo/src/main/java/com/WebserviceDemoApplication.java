package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @description:  https://blog.csdn.net/c99463904/article/details/76018436
 * @author: shurunlong
 * @create: 2020-10-19 16:06
 */
@EnableScheduling
@SpringBootApplication
@EnableCaching
public class WebserviceDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebserviceDemoApplication.class, args);
        System.out.println("启动成功");
    }
}
