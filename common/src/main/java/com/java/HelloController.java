package com.java;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * RESTful API
 * 无需mvc配置，直接访问
 * @author yangzhenyu
 * */
@RestController
public class HelloController {
    private static Logger log = LoggerFactory.getLogger(HelloController.class);
    @Autowired
    private DiscoveryClient client;
    @Autowired
    private Environment environment;
    @RequestMapping(value = "/hello" ,method = RequestMethod.GET)
    public String index(){
        String name = "spring.application.name"; //本地服务名
        List<ServiceInstance> instances = client.getInstances(environment.getProperty(name));
        //输出的内容就是我们向注册中心注册的
        return instances.size()!=0?String.format("host:[%s],service_id:[%s]",instances.get(0).getHost(),instances.get(0).getServiceId()):"host:[],service_id:[]";
    }
}
