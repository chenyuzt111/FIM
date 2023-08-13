package com.benewake.saleordersystem;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author Lcs
 */
@EnableAsync
@SpringBootApplication
@MapperScan(basePackages = "com.benewake.saleordersystem.mapper")
public class SaleOrderSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(SaleOrderSystemApplication.class, args);
    }

}
