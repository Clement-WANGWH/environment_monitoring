package com.neusoft.ysu.nepmserve;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@MapperScan("com.neusoft.ysu.nepmserve.mapper")
@SpringBootApplication
public class NepmServeApplication {

    public static void main(String[] args) {
        SpringApplication.run(NepmServeApplication.class, args);
    }

}
