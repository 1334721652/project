package com.java.demo.test.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yangzhenyu
 * */
@RestController
@RequestMapping("api/")
public class CommonController {

    @PostMapping("/test")
    public  String test(String id){
        return "common 服务调用成功!!!";
    }
    @RequestMapping(value = "/hello" ,method = RequestMethod.GET)
    public  String hello(){
        return "common 服务调用成功!!!";
    }
}
