package com.shu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @description:
 * @author: shurunlong
 * @create: 2020-10-22 11:19
 */
@SpringBootApplication
@EnableSwagger2
//@EnableJpaRepositories(basePackages = {"com.shu.*"})
//@EntityScan(basePackages = {"com.shu.*"})
public class DataCascadeApplication {
    public static void main(String[] args) {
        SpringApplication.run(DataCascadeApplication.class, args);
        System.out.println("OK");
    }
}
