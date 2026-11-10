package cn.wbnull.springbootconsumer.controller;

import cn.wbnull.springbootconsumer.feign.GatewayFeignClient;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 入口类
 *
 * @author dukunbiao(null)  2019-04-26
 *         https://github.com/dkbnull/SpringCloudDemo
 */
@RestController
@RequiredArgsConstructor
public class GatewayController {

    private final GatewayFeignClient gatewayFeignClient;

    @PostMapping(value = "/users")
    public Map<String, String> users(@RequestBody Map<String, String> request) throws InterruptedException {
        Thread.sleep(2000);

        return gatewayFeignClient.users(request);
    }
}