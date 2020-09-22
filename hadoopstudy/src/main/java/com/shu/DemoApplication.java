package com.shu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
@EnableCaching
public class DemoApplication {

    public static void main(String[] args) {
        //https://www.cnblogs.com/ywjfx/p/11352892.html
//        System.setProperty("hadoop.home.dir", "F:\\softwares\\hadoop\\hadoop-2.4.1");
        SpringApplication.run(DemoApplication.class, args);
    }

}
