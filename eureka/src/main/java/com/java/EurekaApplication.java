package com.java;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * 启动类
 * @author yangzhenyu
 */
@EnableEurekaServer
@SpringBootApplication(scanBasePackages={"com.java"})
public class EurekaApplication
{
    public static void main( String[] args ){
        SpringApplication.run(EurekaApplication.class, args);
    }
}
