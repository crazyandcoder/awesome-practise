package com.crazyandcoder.university;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@MapperScan("com.crazyandcoder.university.mapper")
public class UniversityApplication extends SpringBootServletInitializer {

    private static final Logger logger = LoggerFactory.getLogger(UniversityApplication.class);

    @Autowired
    private RestTemplateBuilder builder;

    @Bean
    public RestTemplate restTemplate() {
        return builder.build();
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(UniversityApplication.class);
    }



    public static void main(String[] args) {
        logger.info("开始启动！");
        SpringApplication.run(UniversityApplication.class, args);
        logger.info("启动完成！");

    }

}
