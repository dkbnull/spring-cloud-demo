package cn.wbnull.springbootconsumer.feign;

import cn.wbnull.springbootconsumer.fallback.GatewayFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Map;

/**
 * Feign 接口
 *
 * @author dukunbiao(null)  2019-04-26
 *         https://github.com/dkbnull/SpringCloudDemo
 */
@Component
//@FeignClient(value = "spring-boot-provider", fallback = GatewayFallback.class)
@FeignClient(value = "spring-boot-provider", fallbackFactory = GatewayFallbackFactory.class)
public interface GatewayFeignClient {

    @PostMapping(value = "/springbootprovider/users")
    Map<String, String> users(Map<String, String> request);
}
