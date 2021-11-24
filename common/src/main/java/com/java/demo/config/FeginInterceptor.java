package com.java.demo.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.Map;

@Component
public class FeginInterceptor implements RequestInterceptor {
    private final static  String  TOKEN_HEADER = "token";
    //模拟
    private final static  String  TOKEN_PASSWORD = "yangzhenyu";

    //声明式调用标签
    private final static String FEIGN_FLAG = "yzy_feign_o";

    /**
     * feign调用的时候配置远程调用权限
     *
     * */
    @Override
    public void apply(RequestTemplate requestTemplate) {
        //添加验证信息
        // Map<String, String> headers = getHeaders(getHttpServletRequest());
        requestTemplate.header(TOKEN_HEADER,TOKEN_PASSWORD);
        requestTemplate.header(FEIGN_FLAG,"1");


    }

    private HttpServletRequest getHttpServletRequest() {
        try {
            return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        } catch (Exception e) {
            return null;
        }
    }

    private Map<String, String> getHeaders(HttpServletRequest request) {
        Map<String, String> map = new LinkedHashMap<>();
        Enumeration<String> enumeration = request.getHeaderNames();
        while (enumeration.hasMoreElements()) {
            String key = enumeration.nextElement();
            String value = request.getHeader(key);
            map.put(key, value);
        }
        return map;
    }
}
