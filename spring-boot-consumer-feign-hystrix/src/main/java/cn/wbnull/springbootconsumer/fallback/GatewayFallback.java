package cn.wbnull.springbootconsumer.fallback;

import cn.wbnull.springbootconsumer.feign.GatewayFeignClient;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 熔断处理类
 *
 * @author dukunbiao(null)  2019-04-26
 *         https://github.com/dkbnull/SpringCloudDemo
 */
@Component
public class GatewayFallback implements GatewayFeignClient {

    @Override
    public Map<String, String> users(Map<String, String> request) {
        request.put("fallback", "spring-boot-consumer-feign-hystrix");

        return request;
    }
}
