package com.java.filters;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 网关自定义错误异常处理
 * @author yangzhenyu
 * */
@Component
public class ErrorFilter extends ZuulFilter {
    private static Logger log = LoggerFactory.getLogger(ErrorFilter.class);

    /**
     * 定义过滤器类型
     * 1、pre:可以在请求被路由之前调用
     * 2、routing:在路由请求时被调用
     * 3、post:在routing和error过滤器之后调用
     * 4、error:处理请求时发生错误被调用
     * */
    @Override
    public String filterType() {
        return "error";
    }

    /**
     * 定义过滤器执行顺序
     * */
    @Override
    public int filterOrder() {
        return 30;
    }

    /**
     * 返回一个布尔值来判断过滤器是否要执行
     * */
    @Override
    public boolean shouldFilter() {
        return true;
    }


    /**
     * 执行过滤器的具体逻辑
     * */
    @Override
    public Object run()  {
        Map<String,Object> map = new HashMap<>(3);
        RequestContext ctx = RequestContext.getCurrentContext();
        Throwable throwable = ctx.getThrowable();
        log.error("调用错误:{}",ctx.get("requestURI")==null?"触发限流超时":"url:"+ctx.get("requestURI"));
        HttpServletResponse response = ctx.getResponse();
        try {
            // 此处自定义响应体start
            map.put("code",ctx.getResponseStatusCode());
            String errorMsg = "";
            while (throwable.getCause() != null){
                throwable = throwable.getCause();
                errorMsg =  throwable.getMessage();
            }
            map.put("message","【当前服务出现异常】");
            map.put("exception",errorMsg);

            // 此处自定义响应体end
            response.setStatus(HttpServletResponse.SC_OK);
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.getOutputStream().write(map.toString().getBytes());
        } catch (IOException e) {
            ReflectionUtils.rethrowRuntimeException(e);
        }
        return null;
    }
}
