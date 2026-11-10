package cn.wbnull.springbootconsumer.controller;

import cn.wbnull.springbootconsumer.feign.GatewayFeignClient;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 入口类
 *
 * @author dukunbiao(null)  2019-04-25
 *         https://github.com/dkbnull/SpringCloudDemo
 */
@RestController
@RequiredArgsConstructor
public class GatewayController {

    private final GatewayFeignClient gatewayFeignClient;

    @GetMapping(value = "/gateway")
    public String gateway() {
        return gatewayFeignClient.gateway();
    }

    @PostMapping(value = "/user")
    public String user(@RequestParam(value = "name") String name) {
        return gatewayFeignClient.user(name);
    }

    @PostMapping(value = "/users")
    public Map<String, String> users(@RequestBody Map<String, String> request) {
        return gatewayFeignClient.users(request);
    }
}