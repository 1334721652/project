package com.java;

import com.java.config.processor.Singleton;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * 启动类
 * @author yangzhenyu
 */
@EnableEurekaClient
@SpringBootApplication(scanBasePackages={"com.java"})
public class GatewayApplication {
    public static void main( String[] args ){

        ConfigurableApplicationContext run = SpringApplication.run(GatewayApplication.class, args);
        /**
         * 获取自定义配置的对象(单例)
         * */
        Singleton.getInstance(run.getEnvironment(),"YES");
    }
}
