package cn.wbnull.springbootconsumer.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * 入口类
 *
 * @author dukunbiao(null)  2019-04-12
 *         https://github.com/dkbnull/SpringCloudDemo
 */
@RestController
@RequiredArgsConstructor
public class GatewayController {

    private final RestTemplate restTemplate;

    @GetMapping(value = "/gateway")
    public String gateway() {
        return restTemplate.getForObject("http://spring-boot-provider/springbootprovider/gateway", String.class);
    }
}