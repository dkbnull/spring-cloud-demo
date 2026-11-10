package cn.wbnull.springbootprovider.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 入口类
 *
 * @author dukunbiao(null)  2019-04-12
 * https://github.com/dkbnull/SpringCloudDemo
 */
@RestController
@Api(tags = "测试接口")
public class GatewayController {

    @GetMapping(value = "/gateway")
    @ApiOperation("测试接口")
    public String gateway() {
        return "hello world,this is spring-boot-provider-v2";
    }

    @PostMapping(value = "/user")
    @ApiOperation("用户测试接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "用户名", required = true)
    })
    public String user(@RequestParam(value = "name") String name) {
        return "hello world,this is spring-boot-provider-v2. name is " + name;
    }

    @PostMapping(value = "/users")
    @ApiOperation("批量用户测试接口")
    public Map<String, String> users(@RequestBody Map<String, String> request) {
        request.put("hello world", "spring-boot-provider-v2");

        return request;
    }
}
