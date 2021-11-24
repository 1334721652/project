package com.java.demo.config;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.WeightedResponseTimeRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration

public class RandomRuleConfig {
    /**
     * Ribbon内置的负载均衡策略（7种）
     * 1、RoundRobinRule  轮询（默认策略）
     * 轮询适合节点性能都差不多的情况。从前往后依次轮询节点列表中的每个节点，谁空闲就调用谁。
     *
     * 2、RetryRule  重试
     * 先轮询，如果未获取到节点，则在指定的时间内（默认500ms）重试。
     *
     * 3、RandomRule  随机
     *
     * 4、BestAvailableRule  最可用，选择负载最小的节点
     *
     * 5、AvailabilityFilteringRule  可用过滤
     * 先过滤掉处于断路状态（断路器打开）、负载很大的节点，再使用轮询。
     *
     * 6、ZoneAvoidanceRule  根据大区性能、节点可用性综合筛选
     *
     * 7、WeightedResponseTimeRule  权重响应时间
     * 根据节点的平均响应时间计算权重，响应快的权重大，被选中的机率就越大。
     * */
    //设置负载均衡策略
    @Bean
    public IRule getRule(){
        return  new WeightedResponseTimeRule();
    }

}
