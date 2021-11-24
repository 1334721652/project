package com.java.config;

import com.java.config.route.AccessLogGlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {
    @Bean
    public AccessLogGlobalFilter accessLogGlobalFilter(){
        return new AccessLogGlobalFilter();
    }
}
