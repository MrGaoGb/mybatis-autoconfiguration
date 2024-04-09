package com.mrgao.xbqx;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.mrgao.xbqx.mapper")
public class MybatisAutoconfigurationApplication {

    public static void main(String[] args) {
        SpringApplication.run(MybatisAutoconfigurationApplication.class, args);
    }

}
