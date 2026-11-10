package cn.wbnull.springbootconsumer.feign;

import cn.wbnull.springbootconsumer.config.GlobalConfiguration;
import feign.Headers;
import feign.Param;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Feign 接口
 *
 * @author dukunbiao(null)  2019-04-25
 *         https://github.com/dkbnull/SpringCloudDemo
 */
@Component
//@FeignClient("spring-boot-provider")
@FeignClient(value = "spring-boot-provider", configuration = GlobalConfiguration.class)
public interface GatewayFeignClient {

//    @GetMapping(value = "/springbootprovider/gateway")
//    String gateway() throws Exception;
//
//    @PostMapping(value = "/springbootprovider/user")
//    String user(@RequestParam(value = "name") String name) throws Exception;

    @RequestLine("GET /springbootprovider/gateway")
    String gateway();

    @RequestLine("POST /springbootprovider/user?name={name}")
    String user(@Param("name") String name);

    @RequestLine("POST /springbootprovider/users")
    @Headers("Content-Type: application/json")
    Map<String, String> users(Map<String, String> request);
}
